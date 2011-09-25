package org.soc.gwt.client.game.widgetsAbstract.main;

import org.soc.common.views.widgetsInterface.main.GameWidget;

import com.google.inject.Inject;

public class PlayersPresenter {
  public interface PlayerView {}

  public interface PlayersView {
    public void addPlayerView(PlayerView playerView);
  }

  private PlayersView view;
  private GameWidget gameWidget;

  @Inject public PlayersPresenter(PlayersView view, GameWidget gameWidget) {
    this.view = view;
    this.gameWidget = gameWidget;
  }
}
