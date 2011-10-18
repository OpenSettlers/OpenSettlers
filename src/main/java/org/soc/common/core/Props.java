//* @formatter:off
package org.soc.common.core;

import java.util.*;

import org.soc.common.core.GenericList.ImmutableList;
import org.soc.common.core.GenericList.MutableList;
import org.soc.common.core.Props.PropertyList.PropertyTypeList;
import org.soc.common.core.Props.Value.StringValue;
import org.soc.common.core.property.*;

import com.google.gwt.event.logical.shared.*;
import com.google.gwt.user.client.ui.*;

/** Enable iteration over properties of an object that has properties 
 * 
 * Configuration by extension: extend abstract classes to define propertylist and
 * custom properties
 * 
 * Assumptions:
 *  -Classes being properties are defined as interface, abstract (for custom properties) 
 *    and impl (for static reference)
 *  -Classes having properties have private PropertyList class
 *  -Properties are defined statically
 * 
 * Properties can be "rich": they can contain property meta info like length of string,
 *  nullability, et cetera.
 * Properties, when set, have a parent. 
 * TextProperty: min length, maxlength
 * IdProperty
 * 
 * C --> insert
 * R 
 *  - depth of properties 0, 1, infinite
 *  - Use a filter. -> convert filter into sql filter: 
 * U --> send a map of changes props
 * D --> delete f
 * 
 * 
 * Join
 * Group by
 * 
 * */

public class Props {
  /** 
   * Any class having either getters, setters, or both 
   * With this, you can do: 
   *  
   *  for (Property property : someObject.properties()) {
   *    query.select(property.name())
   *         .from(someObject.name());
   *  }
   *    
   * */
  public interface HasProperties {
    public PropertyTypeList properties();
  }
  
  /** 
   * A readonly property  
   * */
  public interface HasProperty extends HasProperties {
    public Property getProp(Property type);
  }
  
  /** 
   * A write only property 
   * */
  public interface SetsProperty extends HasProperties {
    public void setProp(Property type, Property value);
  }
  
  public interface Value {
    public interface StringValue extends Value {
      public String value();
    }

    public interface IntValue extends Value {
      public int value();
    }

    public interface DoubleValue extends Value {
      public double value();
    }

    public interface DateValue extends Value {
      public java.util.Date value();
    }

    public interface MultiValue extends Value {
      public interface ValueList extends ImmutableList<Value> {}

      public ValueList value();
    }
  }


  


  /** Keeps track of property values, fires events, dispatches to property */
  public static interface PropertyList
    extends
      ImmutableList<Property> {
    
    public <H extends SetsProperty, P extends Property> void set(P type, P value, H hasProp);
    public Property get(Property type);
    public <H extends SetsProperty, P extends Property> void setWithoutDispatch(P type, P value, H hasProp);
    public PropertyTypeList types();
    public void addHandler(Property type, Handler handler);
    public void addHandler(Handler handler);
    
    public static class PropertyTypeList 
      extends 
        ImmutableListImpl<Property> {
      
      public PropertyTypeList(List<Property> properties) { 
        super(properties);
      }
      public PropertyTypeList(Property... properties) {
        super(properties);
      }
      @Override public String toString() {
        return "PropertyTypeList [items=" + items + "]";
      }
    }
    
    public static abstract class Impl
      extends
        AbstractImmutableList<Property>
      implements
        PropertyList {
      
      private final static boolean doDispatch = true;
      private final static boolean ignoreDispatch = false;
      
      private List<Property> types = new ArrayList<Property>();
      private Map<Class, Property> properties = new HashMap<Class, Property>();
      private Map<Class, List<Handler>> handlers = new HashMap<Class, List<Handler>>();
      private List<Handler> geenricHandlers = new ArrayList<Handler>(); 
      
      /** Construct from a list of property types */
      public Impl(Property... items2) {
        for (Property p : items2) {
          properties.put(p.getClass(), null);
          types.add(p);
        }
      }
      /** Returns all the registered property types */
      public PropertyTypeList types() {
        return new PropertyTypeList(types);
      }
      @Override protected List<Property> createList() {
        return new ArrayList<Property>();
      }
      /** Asserts given property is present and returns it */
      public Property get(Property type) {
        assert properties.containsKey(type.getClass());
        return properties.get(type.getClass());
      }
      /** Asserts given type is present and sets the property, dispatching to the property */
      @Override public <H extends SetsProperty, P extends Property> 
          void set(P type, P value, H hasProp) {
        assert properties.containsKey(type.getClass());
        doSet(type, value, hasProp, doDispatch);
      }
      /** Sets target property _without_ dispatching to the property */
      @Override public <H extends SetsProperty, P extends Property> 
          void setWithoutDispatch(P type, P value, H hasProp) {
        doSet(type, value, hasProp, ignoreDispatch);
      }
      /** Sets the new value, dispatches to property and fires event */
      private <H extends SetsProperty, P extends Property> 
          void doSet(P type, P value, H hasProp, boolean setParent) {
        assert value != null && type.canSetToNull();
        properties.put(type.getClass(), value);
        if (setParent)
          value.doSet(hasProp);
        if (type.firesEvent())
          onChanged(type, value, null);
      }
      /** Adds handler for target property */
      @Override public void addHandler(Property type, Handler handler) {
        if (!handlers.containsKey(type.getClass()))
          handlers.put(type.getClass(), new ArrayList<Handler>());
        handlers.get(type.getClass()).add(handler);
      }
      /** Adds handler for any property change event */
      public void addHandler(Handler handler) {
        geenricHandlers.add(handler);
      }
      /** Fires event for target property and generic event */
      public <P extends Property> void onChanged(P type, P p, P old) {
        List<Handler> handlersForType =  handlers.get(type.getClass());
        if (handlersForType != null)
          for (Handler handler : handlersForType)
            handler.changed(p, old);
        for (Handler hanlder : geenricHandlers)
          hanlder.changed(p, old);
      }
    }

  }

  public interface Handler<P extends Property> {
    public void changed(P newProp, P oldProp);
    
    public interface Handle {
      public void remove();
    }
  }

  public interface NotifiesPropertyChange {
    // Type-safe property handling
    public <P extends Property> void addHandler(P type, Handler handler);
    
    // Register handling of any property change
    public void addHandler(Handler handler);
  }

  /*
   * StringValue
   * TextBox
   * model
   * 
   */
  public static class Binder {
    private Property property = null;
    private Value.StringValue stringValue;
    public Binder in(Property property) {
      this.property=property;
      return this;
    }
    
    public StringBinder bindString(Value.StringValue property) {
      return new StringBinder(property);
    }
    public class StringBinder {
      private TextBox textBox;
      private Property property;
      private StringValue stringValue;

      public StringBinder(StringValue stringValue) {
        this.stringValue = stringValue;
      }

      public void to(NotifiesPropertyChange notifiesChange) {
        notifiesChange.addHandler(property, new Handler<Property>() {
          @Override public void changed(Property newProp, Property oldProp) {
            textBox.setValue(stringValue.value());
          }
        });
        textBox.addValueChangeHandler(new ValueChangeHandler<String>() {
          @Override public void onValueChange(ValueChangeEvent<String> event) {
            if (event.getValue() != stringValue.value())
            {}
          }
        });
      }
      
      public StringBinder with(TextBox textbox) {
        this.textBox = textbox;
        return this;
      }
    }
    public void with(Widget widget) {
      
      this.property = null;
    }
  }
  
  public interface Constraint {
    public boolean isMet();
    
    public interface ConstraintList extends ImmutableList<Constraint> {
      public static class Impl 
        extends
          AbstractImmutableList<Constraint>
        implements 
          ConstraintList {

        @Override protected List<Constraint> createList() {
          return new ArrayList<Constraint>();
        }
      }
    }
    
    public interface MutableConstraintList extends MutableList<Constraint> {
      public static class Impl 
        extends 
          MutableListImpl<Constraint>
        implements 
          MutableConstraintList {
      }
    }
  }
}
