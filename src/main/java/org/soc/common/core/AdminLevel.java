//package org.soc.common.core;
//
//import org.soc.common.core.ActivityInfo.AdminLevelList;
//import org.soc.common.core.ActivityInfo.AiModel;
//import org.soc.common.core.ActivityInfo.HasAdminLevel;
//import org.soc.common.core.ActivityInfo.SetsAdminLevel;
//import org.soc.common.core.AdminLevel.AdminLevelId;
//import org.soc.common.core.GenericList.FullImmutableList;
//import org.soc.common.core.GenericList.FullImmutableList.AbstractFullImmutableList;
//import org.soc.common.core.GenericList.HasId;
//import org.soc.common.core.GenericList.Id.IntegerId;
//import org.soc.common.core.GenericList.ImmutableList;
//import org.soc.common.core.GenericList.Model;
//import org.soc.common.core.Props.PropertyList;
//import org.soc.common.core.property.*;
//
//public interface AdminLevel
//        extends
//        AiModel
//        <AdminLevelId,
//        SetsAdminLevel>,
//        HasAdminLevel {
//  //HasAdminLevels { 
//  public class Impl
//          extends
//          AbstractFullImmutableList<AdminLevel, AdminLevelList, AdminLevelId>
//          implements
//          AdminLevelList {
//    public Impl(FullImmutableList<AdminLevel, AdminLevelId>... lists) {
//      super(lists);
//    }
//  }
//
//  /** The static type reference and default implementation */
//  //public static final Property adminLevelProperty = new Impl();
//  public interface AdminLevelId extends IntegerId {
//    public static class Impl
//            extends IntegerId.Impl
//            implements AdminLevelId {
//      public Impl(int id) {
//        super(id);
//      }
//    }
//  }
//
//  public class Implw extends Abstract {
//    @Override public AdminLevel adminLevel() {
//      // TODO Auto-generated method stub
//      return null;
//    }
//    @Override public Property getProp(Property type) {
//      // TODO Auto-generated method stub
//      return null;
//    }
//    @Override public Model copy() {
//      // TODO Auto-generated method stub
//      return null;
//    }
//    @Override public boolean canGet() {
//      // TODO Auto-generated method stub
//      return false;
//    }
//    @Override public boolean supportsPersistence() {
//      // TODO Auto-generated method stub
//      return false;
//    }
//    @Override public void doSet(SetsAdminLevel hasProperty) {
//      // TODO Auto-generated method stub
//    }
//  }
//
//  public abstract static class Abstract
//          extends
//          AiModel.Abstract
//          <AdminLevelId,
//          SetsAdminLevel>
//          implements
//          AdminLevel {
//    public static class AdminLevelProperties extends PropertyList.Impl {
//      public AdminLevelProperties() {
//        super(Properties.name);
//      }
//    }
//
//    protected AdminLevel parent;
//    protected AdminLevelId id;
//
//    @Override public AdminLevel adminLevel() {
//      // TODO Auto-generated method stub
//      return null;
//    }
//    @Override public Model copy() {
//      // TODO Auto-generated method stub
//      return null;
//    }
//    @Override public boolean canGet() {
//      // TODO Auto-generated method stub
//      return false;
//    }
//    @Override public boolean supportsPersistence() {
//      // TODO Auto-generated method stub
//      return false;
//    }
//    @Override public void doSet(SetsAdminLevel hasProperty) {
//      // TODO Auto-generated method stub
//    }
//  }
//
//  public interface Country extends AdminLevel {
//    public interface CountryList
//            extends
//            ImmutableList<Country> {
//      public static abstract class Abstract
//              extends
//              ImmutableListImpl<Country>
//              implements
//              CountryList {
//        public Abstract(Country... items) {
//          super(items);
//        }
//      }
//    }
//
//    public interface Supported {
//      public static final Drc drc = new Drc();
//      public static final Haiti haiti = new Haiti();
//    }
//
//    public static class Drc extends Abstract implements Country {
//      public Drc() {
//        super();
//        parent = null;
//        id = new AdminLevelId.Impl(1);
//      }
//      @Override public Property getProp(Property type) {
//        // TODO Auto-generated method stub
//        return null;
//      }
//    }
//
//    public static class Haiti extends Abstract implements Country {
//      public Haiti() {
//        super();
//        parent = null;
//        id = new AdminLevelId.Impl(2);
//      }
//      @Override public HasId setId(AdminLevelId id) {
//        // TODO Auto-generated method stub
//        return null;
//      }
//      @Override public Property getProp(Property type) {
//        // TODO Auto-generated method stub
//        return null;
//      }
//    }
//
//    public static final class SupportedCountries
//            extends
//            CountryList.Abstract {
//      public SupportedCountries() {
//        super(Supported.drc, Supported.haiti);
//      }
//    }
//
//    //public static class Province extends AdminLevel {}
//    public interface Territoire extends AdminLevel {}
//
//    public interface Region extends AdminLevel {}
//  }
//}