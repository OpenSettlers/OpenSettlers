package org.soc.common.presenters;

import org.soc.common.game.GameStatus;

public class GameStatusesPresenter {
  public interface GameStatusesView {}

  public interface GameStatusView<S extends GameStatus> {
    public void setInStatus();
    public void notInStatus();
  }
}
