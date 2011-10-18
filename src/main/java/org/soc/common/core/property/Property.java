package org.soc.common.core.property;

import org.soc.common.core.Props.SetsProperty;

/** All objects acting as properties have standard metadata and behaviour
 * such as firing events on change, nullability persistability. Add yours for optimum flavor.
 * @formatter:off */
public interface Property<H extends SetsProperty> {
  public boolean canGet();                     /** assert canGet()] */
  public boolean canSet();
  public boolean isvalid();                    /** return rules().areAllMet() */
  public boolean supportsPersistence();        /** assert instanceof Serializable && new T() */
  public boolean canReturnNull();              /** assert canReturnNull && this.property != null */
  public void doSet(H hasProperty);            /** Dispatch setting to the property itself */
  public boolean canSetToNull();               /** assert canSetToNull() && value != null */
  public boolean firesEvent();

  public abstract class AbstractProperty<H extends SetsProperty>
    implements
      Property<H> {
    
    protected boolean canGet = false;          /** All false by default: explicitly set true in base */
    protected boolean canGetNull = false;
    protected boolean canSet = false;
    protected boolean canSetNull = false;
    protected boolean isValid = false;
    protected boolean firesEvent = false;
    protected boolean supportsPersistence;

    protected AbstractProperty() {}
    @Override public boolean canSet() {
      return canSet;
    }
    @Override public boolean canSetToNull() {
      return canSetNull;
    }
    @Override public boolean canReturnNull() {
      return canGetNull;
    }
    @Override public boolean firesEvent() {
      return firesEvent;
    }
    @Override public boolean isvalid() {
      return isValid;
    }
    @Override public boolean canGet() {
      return canGet;
    }
    @Override public boolean supportsPersistence() {
      return supportsPersistence;
    }
  }
}