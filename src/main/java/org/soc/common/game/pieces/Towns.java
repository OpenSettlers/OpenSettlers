package org.soc.common.game.pieces;

import org.soc.common.core.GenericList.FullImmutableList;
import org.soc.common.core.GenericList.MutableList.MutableModelList;
import org.soc.common.game.board.*;

//@formatter:off
public class Towns {
  public interface TownList
    extends
      FullImmutableList<Town, Integer> {
    
    public Town get(HexPoint pointLocation);
    public boolean containsPoint(HexPoint point);

    public class Impl
            extends
            AbstractFullImmutableList<Town, TownList, Integer>
            implements
            TownList {
      
      public Impl(MutableTownList towns) {
        super(towns);
      }
      
      public Town get(HexPoint pointLocation) {
        for (Town town : this)
          if (town.point().equals(pointLocation))
            return town;
        return null;
      }
    
      public boolean containsPoint(HexPoint point) {
        for (Town town : this)
          if (town.point().equals(point))
            return true;
        return false;
      }
    }
  }

  public interface MutableTownList
    extends
      MutableModelList<Town, TownList, Integer>,
      TownList {
    
    public class Impl
      extends
        FullMutableListImpl<Town, TownList, Integer>
      implements
        MutableTownList {

      @Override public TownList toImmutableModels() {
        return new TownList.Impl(this);
      }

      @Override public Town get(HexPoint pointLocation) {
        return toImmutableModels().get(pointLocation);
      }

      @Override public boolean containsPoint(HexPoint point) {
        return toImmutableModels().containsPoint(point);
      }
      
    }
  }
}
