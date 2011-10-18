package org.soc.common.core.property;

import org.soc.common.core.Props.HasProperty;
import org.soc.common.core.Props.SetsProperty;
import org.soc.common.core.Props.Value;
import org.soc.common.core.Props.Value.DateValue;
import org.soc.common.core.Props.Value.MultiValue;
import org.soc.common.core.property.Properties.Date.SetsDate;
import org.soc.common.core.property.Properties.DateRange.SetsDateRange;
import org.soc.common.core.property.Properties.Description.SetsDescription;
import org.soc.common.core.property.Properties.LatLong.SetsLatLong;
import org.soc.common.core.property.Properties.Name.SetsName;

/** Commonly used properties 
 *  Write your own properties or derive from an Abstract class  */
public class Properties {
  /** When using these, ensure ONE property per class. If you need multiple properties of the same 
   * type, subclass and define static final instances in that class */
  public static final Name name = new Name.Impl("");
  public static final Description description = new Description.Impl("descriptions.thisClassName");
  public static final DateRange dateRange = new DateRange.Impl();
  public static final Date date = new Date.Impl();

  public interface Name extends Property<SetsName>, Value.StringValue {
    public static interface HasName extends HasProperty {
      public Name name();
    }

    public interface SetsName extends SetsProperty {
      public void setName(Name name);
    }

    public static class Impl extends Abstract {
      public Impl(String theName) {
        super(theName);
        canSet = true;
        canSetNull = true;
        canGetNull = true;
        firesEvent = false;
      }
    }

    public static abstract class Abstract extends AbstractProperty<SetsName> implements Name {
      protected String theName;

      public Abstract(String theName) {
        this.theName = theName;
      }
      @Override public String value() {
        return theName;
      }
      @Override public void doSet(SetsName setsName) {
        setsName.setName(this);
      }
    }
  }

  public interface Description extends Property<SetsDescription>, Value.StringValue {
    public interface HasDescription extends HasProperty {
      public Description description();
    }

    public interface SetsDescription extends SetsProperty {
      public void setDescription(Description description);
    }

    public static class Impl extends Abstract {
      public Impl(String description) {
        super(description);
      }
      @Override public void doSet(SetsDescription hasDescription) {}
    }

    public static abstract class Abstract
            extends
            AbstractProperty<SetsDescription>
            implements
            Description {
      protected String description = "";

      public Abstract(String description) {
        this.description = description;
      }
      @Override public void doSet(SetsDescription setsDescription) {
        setsDescription.setDescription(this);
      }
      @Override public String value() {
        return description;
      }
    }
  }

  public interface LatLong extends Property<SetsLatLong> {
    public interface HasLatLong extends HasProperty {
      public LatLong latLong();
    }

    public interface SetsLatLong extends SetsProperty {
      public void setLatLong(LatLong latLong);
    }

    public static abstract class Abstract
            extends
            AbstractProperty<SetsLatLong>
            implements
            LatLong {
      @Override public void doSet(SetsLatLong setsLatLong) {
        setsLatLong.setLatLong(this);
      }
    }
  }

  public interface Date extends Property<SetsDate>, DateValue {
    public interface HasDate extends HasProperty {
      public Date date();
    }

    public interface SetsDate extends SetsProperty {
      public void setDate(Date date);
    }

    public static class Impl
            extends
            Abstract
            implements
            Date {
      public Impl(java.util.Date date) {
        super(date);
      }
      public Impl() {
        super();
      }
    }

    public static abstract class Abstract
            extends
            AbstractProperty<SetsDate>
            implements
            Date {
      protected java.util.Date date;

      public Abstract() {/** Default serializable */
      }
      public Abstract(java.util.Date date) {
        this.date = date;
      }
      @Override public java.util.Date value() {
        return date;
      }
      @Override public void doSet(SetsDate hasdate) {
        hasdate.setDate(this);
      }
    }
  }

  public interface DateRange extends Property<SetsDateRange>, MultiValue {
    public interface HasDateRange extends HasProperty {
      public DateRange getDateRange();
    }

    public interface SetsDateRange extends SetsProperty {
      public void setDateRange(DateRange dateRange);
    }

    public static class Impl extends Abstract {
      public Impl(java.util.Date date1, java.util.Date date2) {
        super(date1, date2);
      }
      public Impl() {}
    }

    public static abstract class Abstract
            extends
            AbstractProperty<SetsDateRange>
            implements
            DateRange {
      protected java.util.Date date1;
      protected java.util.Date date2;

      protected Abstract() {}
      public Abstract(java.util.Date date1, java.util.Date date2) {
        this.date1 = date1;
        this.date2 = date2;
      }
      @Override public void doSet(SetsDateRange hasDateRange) {
        hasDateRange.setDateRange(this);
      }
      @Override public ValueList value() {
        // TODO Auto-generated method stub
        return null;
      }
    }
  }
}
