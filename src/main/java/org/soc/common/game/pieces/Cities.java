package org.soc.common.game.pieces;

import org.soc.common.core.GenericList.FullImmutableList;
import org.soc.common.core.GenericList.MutableList.MutableModelList;

//@formatter:off
public class Cities {
  public interface CityList
    extends
      FullImmutableList<City, Integer> {
    
    public class Impl
      extends
        AbstractFullImmutableList<City, CityList, Integer>
      implements
        CityList {
      
    }
  }

  public interface MutableCityList
    extends
      MutableModelList<City, CityList, Integer> {
    
    public class Impl
      extends
        FullMutableListImpl<City, CityList, Integer>
      implements
        MutableCityList {
    }
  }
}
