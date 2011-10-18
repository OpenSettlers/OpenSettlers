package org.soc.common.game;

import org.soc.common.core.GenericList.*;
import org.soc.common.core.GenericList.FullImmutableList.*;
import org.soc.common.core.GenericList.MutableList.*;

public class VictoryPoints {
  public interface VictoryPointsList extends FullImmutableList<VictoryPointItem, Integer> {
    public int total();
  }

  public interface MutableVictoryPointsList
          extends
          MutableModelList<VictoryPointItem, VictoryPointsList, Integer>,
          VictoryPointsList {
    public VictoryPointsList toImmutableModels();

    public class MutableVictoryPointsListImpl
            extends
            FullMutableListImpl<VictoryPointItem, VictoryPointsList, Integer>
            implements
            MutableVictoryPointsList {
      @Override public int total() {
        return toImmutableModels().total();
      }
      @Override public VictoryPointsList toImmutableModels() {
        return new VictoryPointsListImpl(super.toImmutable());
      }
    }
  }

  public static class VictoryPointsListImpl
          extends AbstractFullImmutableList<VictoryPointItem, VictoryPointsList, Integer>
          implements VictoryPointsList {
    public VictoryPointsListImpl(VictoryPointsList mutableVictoryPointsListImpl) {
      // TODO Auto-generated constructor stub
    }
    public VictoryPointsListImpl(ImmutableList<VictoryPointItem> immutable) {
      super(immutable);
    }
    /** Returns total amount of victory points in this list */
    public int total() {
      int result = 0;
      for (VictoryPointItem vp : this)
        result += vp.victoryPoints();
      return result;
    }
  }
}
