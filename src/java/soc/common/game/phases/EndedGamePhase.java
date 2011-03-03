package soc.common.game.phases;

import soc.common.game.Game;
import soc.common.game.Turn;
import soc.common.views.widgetsInterface.main.GamePhaseStatusWidget;
import soc.common.views.widgetsInterface.main.GamePhaseStatusWidgetFactory;

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

    @Override
    public GamePhase next(Game game)
    {
        return null;
    }
}