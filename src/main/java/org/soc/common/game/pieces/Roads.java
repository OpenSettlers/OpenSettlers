package org.soc.common.game.pieces;

import org.soc.common.core.GenericList.FullImmutableList;
import org.soc.common.core.GenericList.MutableList.MutableModelList;
import org.soc.common.game.board.*;

// @formatter:off
public class Roads {
  
  public interface RoadList
    extends
      FullImmutableList<Road, Integer> {

    public boolean containsSide(HexSide side);
    
    public class Impl
      extends 
        AbstractFullImmutableList<Road, RoadList, Integer> 
      implements
        RoadList {
      
      public Impl(MutableRoadList roads) {
        super(roads);
      }

      public boolean containsSide(HexSide side)    {
        for (Road road : this)
            if (road.getSide().equals(side)) 
                return true;

        return false;
      }
    }
  }

  public interface MutableRoadList
    extends
      MutableModelList<Road, RoadList, Integer>,
      RoadList {
    
    public class Impl
      extends
        FullMutableListImpl<Road, RoadList, Integer>
      implements
        MutableRoadList {

      @Override public boolean containsSide(HexSide side) {
        return toImmutableModels().containsSide(side);
      }

      @Override public RoadList toImmutableModels() {
        return new RoadList.Impl(this);
      }
      
    }
  }
}
