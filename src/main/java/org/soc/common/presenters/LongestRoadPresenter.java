package org.soc.common.presenters;

import org.soc.common.game.GamePlayer;

public class LongestRoadPresenter {
  public interface LongestRoadView {
    public void setLength(int length);
    public void setPlayer(GamePlayer player);
    public void setHasLongestRoad();
    public void setLostLongestRoad();
  }
}
