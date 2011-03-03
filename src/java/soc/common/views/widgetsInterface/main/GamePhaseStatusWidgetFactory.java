package soc.common.views.widgetsInterface.main;

import soc.common.game.phases.DetermineFirstPlayerGamePhase;
import soc.common.game.phases.EndedGamePhase;
import soc.common.game.phases.InitialPlacementGamePhase;
import soc.common.game.phases.LobbyGamePhase;
import soc.common.game.phases.PlacePortsGamePhase;
import soc.common.game.phases.PlayTurnsGamePhase;

public interface GamePhaseStatusWidgetFactory
{
    public GamePhaseStatusWidget createDetermineFirstPlayerStatusWidget(
            DetermineFirstPlayerGamePhase determineFirstPlayerGamePhase);

    public GamePhaseStatusWidget createInitialPlacementStatusWidget(
            InitialPlacementGamePhase initialPlacementGamePhase);

    public GamePhaseStatusWidget createLobbyStatusWidget(
            LobbyGamePhase lobbyGamePhase);

    public GamePhaseStatusWidget createPlayTurnsStatusWidget(
            PlayTurnsGamePhase playTurnsGamePhase);

    public GamePhaseStatusWidget createEndedStatusWidget(
            EndedGamePhase endedGamePhase);

    public GamePhaseStatusWidget createPlacePortsStatusWidget(
            PlacePortsGamePhase placePortsGamePhase);
}
