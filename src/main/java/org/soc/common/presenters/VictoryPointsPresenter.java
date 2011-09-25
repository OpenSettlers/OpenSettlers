package org.soc.common.presenters;

import org.soc.common.game.VictoryPointItem;

public class VictoryPointsPresenter {
  public interface VictoryPointsView {
    public void addPoint(VictoryPointItem addedPoint);
    public void removePoint(VictoryPointItem removedPoint);
  }
}
