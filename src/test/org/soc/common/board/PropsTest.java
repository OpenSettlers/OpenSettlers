package org.soc.common.board;

import org.junit.*;
import org.soc.common.board.PropsTest.Woei.WoeiImpl;
import org.soc.common.board.PropsTest.Woei.WoeiName;
import org.soc.common.core.Props.Binder;
import org.soc.common.core.Props.Handler;
import org.soc.common.core.Props.NotifiesPropertyChange;
import org.soc.common.core.Props.PropertyList;
import org.soc.common.core.Props.PropertyList.PropertyTypeList;
import org.soc.common.core.property.*;
import org.soc.common.core.property.Properties.Description;
import org.soc.common.core.property.Properties.Description.HasDescription;
import org.soc.common.core.property.Properties.Description.SetsDescription;
import org.soc.common.core.property.Properties.Name;
import org.soc.common.core.property.Properties.Name.HasName;
import org.soc.common.core.property.Properties.Name.SetsName;

import com.google.gwt.user.client.ui.*;

import static org.junit.Assert.*;

public class PropsTest {
  private boolean hasChanged = false;

  /** Perfect inheritance example:
   * Woei _is_ a type that _has_ a name
   * Woei _is_ a type that _has_ properties 
   */
  public interface Woei
          extends
          HasName, SetsName,
          HasDescription, SetsDescription,
          NotifiesPropertyChange {
    /** Extend properties and set rule values after calling the super constructor */
    public static class WoeiName extends Name.Abstract {
      public WoeiName(String theName) {
        super(theName);
        canSetNull = false;
        firesEvent = true;
        isValid = true;
        canGetNull = true;
      }
      @Override public void doSet(SetsName hasProperty) {
        // TODO Auto-generated method stub
      }
    }

    public static class WoeiImpl
            implements Woei {
      private PropertyTypeList propertyTypes = new PropertyTypeList(Properties.name);
      private PropertyList properties = null;// new PropertyList.Impl();
      private Name name;
      private Description description;

      @Override public Property getProp(Property type) {
        return properties.get(type);
      }
      @Override public PropertyTypeList properties() {
        return properties.types();
      }
      @Override public Name name() {
        return name;
      }
      @Override public void setName(Name name) {
        this.name = name;
        properties.setWithoutDispatch(Properties.name, name, this);
      }
      @Override public void setProp(Property type, Property value) {
        properties.set(type, value, this);
      }
      @Override public Description description() {
        return description;
      }
      @Override public void setDescription(Description description) {
        this.description = description;
        properties.setWithoutDispatch(Properties.description, description, this);
      }
      @Override public <P extends Property> void addHandler(P type, Handler handler) {
        properties.addHandler(type, handler);
      }
      @Override public void addHandler(Handler handler) {
        properties.addHandler(handler);
      }
    }
  }

  @Test public void test() {
    Woei woei = new WoeiImpl();
    WoeiName woeiName = new WoeiName("tegek");
    woei.setName(woeiName);
    Object fromGenericGet = woei.getProp(Properties.name);
    Name fromTypeSafeGet = woei.name();
    assertEquals(woeiName, fromGenericGet);
    assertEquals(woeiName, fromTypeSafeGet);
    assertTrue(woei.properties().contains(Properties.name));
  }
  public void tesxt() {
    Woei woei = new WoeiImpl();
    TextBox textbox = new TextBox();
    Binder binder = new Binder();
    binder.bindString(Properties.name).with(textbox).to(woei);
  }
  @Test public void testEvent() {
    Woei woei = new WoeiImpl();
    final WoeiName woeiName = new WoeiName("tegek");
    woei.<Name> addHandler(Properties.name, new Handler<Name>() {
      @Override public void changed(Name newProp, Name oldProp) {
        hasChanged = true;
        assertTrue(oldProp == null);
        assertEquals(newProp, woeiName);
      }
    });
    woei.addHandler(new Handler<Property>() {
      @Override public void changed(Property newProp, Property oldProp) {
        hasChanged = true;
        assertTrue(oldProp == null);
        assertEquals(newProp, woeiName);
      }
    });
    woei.setName(woeiName);
    woei.setDescription(new Description.Impl("Haha!"));
    assertTrue(hasChanged);
  }
  @Test public void testGenericSet() {
    Woei woei = new WoeiImpl();
    WoeiName woeiName = new WoeiName("tegek");
    woei.setProp(Properties.name, woeiName);
    Object fromGenericGet = woei.getProp(Properties.name);
    Object fromTypeSafeGet = woei.name();
    assertEquals(woeiName, fromGenericGet);
    assertEquals(woeiName, fromTypeSafeGet);
    assertTrue(woei.properties().contains(Properties.name));
  }
  @Test public void testSupportedProperties() {
    //    for (Property property : ActivityInfo.supportedProperties) {
    //      if (!(property instanceof Value))
    //        fail("Expected " + Util.shortName(property.getClass()) + " to derive from a value");
    //    }
  }
  @Test public void testAllModels() {
    //for (AiModel model : ActivityInfo.supportedModels) {
    // }
  }
}
