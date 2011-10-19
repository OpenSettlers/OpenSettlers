package org.soc.common.board;

import org.junit.*;
import org.soc.common.board.PropsTest.Woei.WoeiImpl;
import org.soc.common.core.Props.Handler;
import org.soc.common.core.Props.IsChild;
import org.soc.common.core.Props.NotifiesPropertyChange;
import org.soc.common.core.Props.PropertyList;
import org.soc.common.core.Props.PropertyList.PropertyTypeList;
import org.soc.common.core.property.Properties.Description;
import org.soc.common.core.property.Properties.Description.HasDescription;
import org.soc.common.core.property.Properties.Description.SetsDescription;
import org.soc.common.core.property.Properties.Name;
import org.soc.common.core.property.Properties.Name.HasName;
import org.soc.common.core.property.Properties.Name.SetsName;
import org.soc.common.core.property.*;
import org.soc.common.core.property.StaticProperty.AbstractProperty;

import static org.junit.Assert.*;

// @formatter:off
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
    public static WoeiName nameP = new WoeiName();
    public static WoeiDescription descriptionP = new WoeiDescription();

    /** Extend properties and set rule values after calling the super constructor */
    public static class WoeiName extends AbstractProperty {
      public WoeiName() {
        canSetNull = false;
        firesEvent = true;
        isValid = true;
        canGetNull = true;
      }
    }

    public static class WoeiDescription extends AbstractProperty {
      public WoeiDescription() {
        canSetNull = false;
        firesEvent = true;
        isValid = true;
        canGetNull = true;
      }
    }

    public static class WoeiImpl
            implements Woei {
      private PropertyTypeList propertyTypes = new PropertyTypeList();
      private PropertyList properties = new PropertyList.Impl(nameP, descriptionP);
      private Name name;
      private Description description;

      @Override public IsChild getProp(StaticProperty type) {
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
        properties.setWithoutDispatch(nameP, name, this);
      }
      @Override public void setProp(StaticProperty type, IsChild value) {
        properties.set(type, value, this);
      }
      @Override public Description description() {
        return description;
      }
      @Override public void setDescription(Description description) {
        this.description = description;
        properties.setWithoutDispatch(descriptionP, description, this);
      }
      @Override public void addHandler(StaticProperty type, Handler handler) {
        properties.addHandler(type, handler);
      }
      @Override public void addHandler(Handler handler) {
        properties.addHandler(handler);
      }
    }
  }

  @Test public void test() {
    Woei woei = new WoeiImpl();
    Name woeiName = new Name.Impl("tegek");
    woei.setName(woeiName);
    Object fromGenericGet = woei.getProp(Woei.nameP);
    Name fromTypeSafeGet = woei.name();
    assertEquals(woeiName, fromGenericGet);
    assertEquals(woeiName, fromTypeSafeGet);
    assertTrue(woei.properties().contains(Woei.nameP));
  }
  @Test public void testEvent() {
    Woei woei = new WoeiImpl();
    final Name woeiName = new Name.Impl("tegek");
    final Description woeiDescription = new Description.Impl("Ha!");
    woei.addHandler(Woei.nameP, new Handler<Name>() {
      @Override public void changed(Name newProp, Name oldProp) {
        hasChanged = true;
        assertTrue(oldProp == null);
        assertEquals(newProp, woeiName);
      }
    });
    woei.addHandler(new Handler() {
      @Override public void changed(IsChild newProp, IsChild oldProp) {
        hasChanged = true;
        assertTrue(oldProp == null);
        assertTrue(newProp.equals(woeiName) || newProp.equals(woeiDescription));
      }
    });
    woei.setName(woeiName);
    woei.setDescription(new Description.Impl("Haha!"));
    assertTrue(hasChanged);
  }
  @Test public void testGenericSet() {
    Woei woei = new WoeiImpl();
    Name woeiName = new Name.Impl("tegek");
    woei.setProp(Woei.nameP, woeiName);
    Object fromGenericGet = woei.getProp(Woei.nameP);
    Object fromTypeSafeGet = woei.name();
    assertEquals(woeiName, fromGenericGet);
    assertEquals(woeiName, fromTypeSafeGet);
    assertTrue(woei.properties().contains(Woei.nameP));
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
