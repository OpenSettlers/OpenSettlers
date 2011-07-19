package org.soc.gwt.client.game.widgetsBitmap.main;

import org.soc.common.game.phases.DetermineFirstPlayerGamePhase;
import org.soc.common.game.phases.EndedGamePhase;
import org.soc.common.game.phases.InitialPlacementGamePhase;
import org.soc.common.game.phases.LobbyGamePhase;
import org.soc.common.game.phases.PlacePortsGamePhase;
import org.soc.common.game.phases.PlayTurnsGamePhase;
import org.soc.common.views.widgetsInterface.main.GamePhaseStatusWidget;
import org.soc.common.views.widgetsInterface.main.GamePhaseStatusWidgetFactory;
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
