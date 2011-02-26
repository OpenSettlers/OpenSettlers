package soc.gwtClient.game.widgetsBitmap.main;

import soc.common.game.gamePhase.DetermineFirstPlayerGamePhase;
import soc.common.game.gamePhase.EndedGamePhase;
import soc.common.game.gamePhase.InitialPlacementGamePhase;
import soc.common.game.gamePhase.LobbyGamePhase;
import soc.common.game.gamePhase.PlacePortsGamePhase;
import soc.common.game.gamePhase.PlayTurnsGamePhase;
import soc.common.views.widgetsInterface.main.GamePhaseStatusWidget;
import soc.common.views.widgetsInterface.main.GamePhaseStatusWidgetFactory;
import soc.common.views.widgetsInterface.main.GameWidget;
import soc.gwtClient.game.widgetsBitmap.status.DetermineFirstPlayerBitmapWidget;
import soc.gwtClient.game.widgetsBitmap.status.EndedBitmapWidget;
import soc.gwtClient.game.widgetsBitmap.status.InitialPlacementBitmapWidget;
import soc.gwtClient.game.widgetsBitmap.status.LobbyBitmapWidget;
import soc.gwtClient.game.widgetsBitmap.status.PlayTurnsBitmapWidget;

public class GamePhaseStatusBitmapWidgetFactory implements
        GamePhaseStatusWidgetFactory
{
    private GameWidget gameWidget;

    public GamePhaseStatusBitmapWidgetFactory(GameWidget gameWidget)
    {
        super();
        this.gameWidget = gameWidget;
    }

    @Override
    public GamePhaseStatusWidget createDetermineFirstPlayerStatusWidget(
            DetermineFirstPlayerGamePhase determineFirstPlayerGamePhase)
    {
        return new DetermineFirstPlayerBitmapWidget(
                determineFirstPlayerGamePhase);
    }

    @Override
    public GamePhaseStatusWidget createEndedStatusWidget(
            EndedGamePhase endedGamePhase)
    {
        return new EndedBitmapWidget(endedGamePhase);
    }

    @Override
    public GamePhaseStatusWidget createInitialPlacementStatusWidget(
            InitialPlacementGamePhase initialPlacementGamePhase)
    {
        return new InitialPlacementBitmapWidget(initialPlacementGamePhase);
    }

    @Override
    public GamePhaseStatusWidget createLobbyStatusWidget(
            LobbyGamePhase lobbyGamePhase)
    {
        return new LobbyBitmapWidget(lobbyGamePhase);
    }

    @Override
    public GamePhaseStatusWidget createPlacePortsStatusWidget(
            PlacePortsGamePhase placePortsGamePhase)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GamePhaseStatusWidget createPlayTurnsStatusWidget(
            PlayTurnsGamePhase playTurnsGamePhase)
    {
        return new PlayTurnsBitmapWidget(gameWidget, playTurnsGamePhase);
    }

}
