package soc.common.actions.gameAction.turnActions.standard;

import soc.common.actions.gameAction.AbstractGameAction;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.PlayTurnsGamePhase;
import soc.common.game.gamePhase.turnPhase.BuildingTurnPhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;

public class NewLongestRoute extends AbstractGameAction
{
    private static final long serialVersionUID = 4790776659374464944L;

    /*
     * Server sent action: no todo message
     * 
     * @see soc.common.actions.Action#getToDoMessage()
     */
    @Override
    public String getToDoMessage()
    {
        return null;
    }

    @Override
    public boolean isAllowed(TurnPhase turnPhase)
    {
        return turnPhase instanceof BuildingTurnPhase;
    }

    @Override
    public boolean isAllowed(GamePhase gamePhase)
    {
        return gamePhase instanceof PlayTurnsGamePhase;
    }

}
