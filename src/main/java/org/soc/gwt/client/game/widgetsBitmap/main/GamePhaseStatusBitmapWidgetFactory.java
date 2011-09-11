package org.soc.gwt.client.game.widgetsBitmap.main;

import org.soc.common.game.GamePhase.DetermineFirstPlayerGamePhase;
import org.soc.common.game.GamePhase.EndedGamePhase;
import org.soc.common.game.GamePhase.InitialPlacementGamePhase;
import org.soc.common.game.GamePhase.LobbyGamePhase;
import org.soc.common.game.GamePhase.PlacePortsGamePhase;
import org.soc.common.game.GamePhase.PlayTurnsGamePhase;
import org.soc.common.views.widgetsInterface.main.GamePhaseWidget;
import org.soc.common.views.widgetsInterface.main.GamePhaseWidget.GamePhaseStatusWidgetFactory;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsBitmap.status.DetermineFirstPlayerBitmapWidget;
import org.soc.gwt.client.game.widgetsBitmap.status.EndedBitmapWidget;
import org.soc.gwt.client.game.widgetsBitmap.status.InitialPlacementBitmapWidget;
import org.soc.gwt.client.game.widgetsBitmap.status.LobbyBitmapWidget;
import org.soc.gwt.client.game.widgetsBitmap.status.PlayTurnsBitmapWidget;

public class GamePhaseStatusBitmapWidgetFactory implements
        GamePhaseStatusWidgetFactory
{
  private GameWidget gameWidget;

  public GamePhaseStatusBitmapWidgetFactory(GameWidget gameWidget)
  {
    super();
    this.gameWidget = gameWidget;
  }
  @Override public GamePhaseWidget createDetermineFirstPlayerPhaseWidget(
          DetermineFirstPlayerGamePhase determineFirstPlayerGamePhase)
  {
    return new DetermineFirstPlayerBitmapWidget(
            determineFirstPlayerGamePhase);
  }
  @Override public GamePhaseWidget createEndedPhaseWidget(
          EndedGamePhase endedGamePhase)
  {
    return new EndedBitmapWidget(endedGamePhase);
  }
  @Override public GamePhaseWidget createInitialPlacementPhaseWidget(
          InitialPlacementGamePhase initialPlacementGamePhase)
  {
    return new InitialPlacementBitmapWidget(initialPlacementGamePhase);
  }
  @Override public GamePhaseWidget createLobbyPhaseWidget(
          LobbyGamePhase lobbyGamePhase)
  {
    return new LobbyBitmapWidget(lobbyGamePhase);
  }
  @Override public GamePhaseWidget createPlacePortsPhaseWidget(
          PlacePortsGamePhase placePortsGamePhase)
  {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public GamePhaseWidget createPlayTurnsPhaseWidget(
          PlayTurnsGamePhase playTurnsGamePhase)
  {
    return new PlayTurnsBitmapWidget(gameWidget, playTurnsGamePhase);
  }
}
