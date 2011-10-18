package org.soc.common.game.pieces;

//@formatter:off
import org.soc.common.core.GenericList.FullImmutableList;
import org.soc.common.core.GenericList.FullImmutableList.AbstractFullImmutableList;
import org.soc.common.core.GenericList.MutableList.MutableModelList;
import org.soc.common.core.GenericList.MutableList.MutableModelList.FullMutableListImpl;
import org.soc.common.game.board.*;

public class Ships {
  /** Base implementation for a collection of ships */
  public interface ShipList
    extends
      FullImmutableList<Ship, Integer> {
    
    public boolean containsWithSide(HexSide side);
  }

  public interface MutableShipList
    extends
      MutableModelList<Ship, ShipList, Integer>,
      ShipList {}

  /** Implementation of a collection of ships */
  public static class ShipListImpl
    extends
      AbstractFullImmutableList<Ship, ShipList, Integer>
    implements
      ShipList {
    
    public ShipListImpl(MutableShipListImpl mutableShipListImpl) {
      super(mutableShipListImpl);
    }
    public boolean containsWithSide(HexSide side) {
      for (Ship ship : this)
        if (ship.getSide().equals(side))
          return true;
      return false;
    }
  }

  public static class MutableShipListImpl
    extends
      FullMutableListImpl<Ship, ShipList, Integer>
    implements
      MutableShipList {
    
    public MutableShipListImpl() {
      super();
    }
    @Override public ShipList toImmutableModels() {
      return new ShipListImpl(this);
    }
    @Override public boolean containsWithSide(HexSide side) {
      return toImmutableModels().containsWithSide(side);
    }
  }
}
