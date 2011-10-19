//* @formatter:off
package org.soc.common.core;

import java.util.*;

import org.soc.common.core.GenericList.ImmutableList;
import org.soc.common.core.Props.PropertyList.PropertyTypeList;
import org.soc.common.core.property.*;

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
  
  public interface IsChild<H extends HasSetters> {
    public void doSet(H hasProperty);            /** Dispatch setting to the property itself */
  }
  
  /** 
   * A readonly property  
   * */
  public interface HasGetters extends HasProperties {
    public IsChild getProp(StaticProperty type);
  }
  
  public interface FakeProperty extends HasGetters {
    
  }
  
  /** Write only property */
  public interface HasSetters extends HasProperties {
    public void setProp(StaticProperty type, IsChild value);
  }
  
  /** Because each property needs a dispatch method set(HasProperty parent), simple value types 
   * must be wrapped. */
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

  public interface Handler<P extends IsChild> {
    public void changed(P newProp, P oldProp);
    
    public interface Handle {
      public void remove();
    }
  }

  public interface NotifiesPropertyChange {
    // Type-safe property handling
    public void addHandler(StaticProperty type, Handler handler);
    
    // Register handling of any property change
    public void addHandler(Handler handler);
  }

  /** Keeps track of property values, fires events, dispatches to property */
  public static interface PropertyList
    extends
      ImmutableList<StaticProperty> {
    
    public <H extends HasSetters, P extends IsChild> void set(StaticProperty type, P value, H hasProp);
    public IsChild get(StaticProperty type);
    public <H extends HasSetters, P extends IsChild> void setWithoutDispatch(StaticProperty type, P value, H hasProp);
    public PropertyTypeList types();
    public void addHandler(StaticProperty type, Handler handler);
    public void addHandler(Handler handler);
    
    public static class PropertyTypeList 
      extends 
        ImmutableListImpl<StaticProperty> {
      
      public PropertyTypeList(List<StaticProperty> properties) { 
        super(properties);
      }
      public PropertyTypeList(StaticProperty... properties) {
        super(properties);
      }
      @Override public String toString() {
        return "PropertyTypeList [items=" + items + "]";
      }
    }
    
    public static class Impl
      extends
        AbstractImmutableList<StaticProperty>
      implements
        PropertyList {
      
      private final static boolean doDispatch = true;
      private final static boolean ignoreDispatch = false;
      
      private List<StaticProperty> types = new ArrayList<StaticProperty>();
      private Map<StaticProperty, IsChild> properties = new HashMap<StaticProperty, IsChild>();
      private Map<Class, List<Handler>> handlers = new HashMap<Class, List<Handler>>();
      private List<Handler> geenricHandlers = new ArrayList<Handler>(); 
      
      /** Construct from a list of property types */
      public Impl(StaticProperty... items2) {
        for (StaticProperty p : items2) {
          properties.put(p, null);
          types.add(p);
        }
      }
      /** Returns all the registered property types */
      public PropertyTypeList types() {
        return new PropertyTypeList(types);
      }
      @Override protected List<StaticProperty> createList() {
        return new ArrayList<StaticProperty>();
      }
      /** Asserts given property is present and returns it */
      public IsChild get(StaticProperty type) {
        assert properties.containsKey(type);
        return properties.get(type);
      }
      /** Asserts given type is present and sets the property, dispatching to the property */
      @Override public <H extends HasSetters, P extends IsChild> 
          void set(StaticProperty type, P value, H hasProp) {
        assert properties.containsKey(type.getClass());
        doSet(type, value, hasProp, doDispatch);
      }
      /** Sets target property _without_ dispatching to the property */
      @Override public <H extends HasSetters, P extends IsChild> 
          void setWithoutDispatch(StaticProperty type, P value, H hasProp) {
        doSet(type, value, hasProp, ignoreDispatch);
      }
      /** Sets the new value, dispatches to property and fires event */
      private <H extends HasSetters, P extends IsChild<H>> 
          void doSet(StaticProperty type, P value, H hasProp, boolean setParent) {
        assert value != null && type.canSetToNull();
        properties.put(type, value);
        if (setParent)
          value.doSet(hasProp);
        if (type.firesEvent())
          onChanged(type, value, null);
      }
      /** Adds handler for target property */
      @Override public void addHandler(StaticProperty type, Handler handler) {
        if (!handlers.containsKey(type.getClass()))
          handlers.put(type.getClass(), new ArrayList<Handler>());
        handlers.get(type.getClass()).add(handler);
      }
      /** Adds handler for any property change event */
      public void addHandler(Handler handler) {
        geenricHandlers.add(handler);
      }
      /** Fires event for target property and generic event */
      public <P extends IsChild> void onChanged(StaticProperty type, P p, P old) {
        List<Handler> handlersForType =  handlers.get(type.getClass());
        if (handlersForType != null)
          for (Handler handler : handlersForType)
            handler.changed(p, old);
        for (Handler hanlder : geenricHandlers)
          hanlder.changed(p, old);
      }
    }

  }


}
