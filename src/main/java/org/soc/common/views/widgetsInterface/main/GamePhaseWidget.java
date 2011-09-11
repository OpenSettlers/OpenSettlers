package org.soc.common.views.widgetsInterface.main;

import org.soc.common.game.GamePhase;
import org.soc.common.game.GamePhase.DetermineFirstPlayerGamePhase;
import org.soc.common.game.GamePhase.EndedGamePhase;
import org.soc.common.game.GamePhase.InitialPlacementGamePhase;
import org.soc.common.game.GamePhase.LobbyGamePhase;
import org.soc.common.game.GamePhase.PlacePortsGamePhase;
import org.soc.common.game.GamePhase.PlayTurnsGamePhase;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.IsWidget;

public interface GamePhaseWidget extends IsWidget {
  public ImageResource getIcon();
  public GamePhase getGamePhase();

  public interface GamePhaseStatusWidgetFactory {
    public GamePhaseWidget createDetermineFirstPlayerPhaseWidget(
            DetermineFirstPlayerGamePhase determineFirstPlayerGamePhase);
    public GamePhaseWidget createInitialPlacementPhaseWidget(
            InitialPlacementGamePhase initialPlacementGamePhase);
    public GamePhaseWidget createLobbyPhaseWidget(
            LobbyGamePhase lobbyGamePhase);
    public GamePhaseWidget createPlayTurnsPhaseWidget(
            PlayTurnsGamePhase playTurnsGamePhase);
    public GamePhaseWidget createEndedPhaseWidget(
            EndedGamePhase endedGamePhase);
    public GamePhaseWidget createPlacePortsPhaseWidget(
            PlacePortsGamePhase placePortsGamePhase);
  }
}
