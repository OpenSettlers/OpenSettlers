package soc.common.game.gamePhase;

import soc.common.game.Game;
import soc.common.game.Turn;
import soc.gwtClient.game.widgetsBitmap.status.GamePhaseStatusWidget;
import soc.gwtClient.game.widgetsInterface.main.GamePhaseStatusWidgetFactory;

public class EndedGamePhase extends AbstractGamePhase
{
    private static final long serialVersionUID = -3286474892624586180L;

    @Override
    public String getMessage()
    {
        // TODO fix message
        return "Game has ended";
    }

    /*
     * Returns null, there is no next turn when game ended
     * 
     * @see
     * soc.common.game.gamePhase.AbstractGamePhase#nextTurn(soc.common.game.
     * Game)
     */
    @Override
    public Turn nextTurn(Game game)
    {
        return null;
    }

    @Override
    public boolean isEnded()
    {
        return true;
    }

    @Override
    public GamePhaseStatusWidget createGamePhaseStatusWidget(
            GamePhaseStatusWidgetFactory factory)
    {
        return factory.createEndedStatusWidget(this);
    }
}