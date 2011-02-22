package soc.common.actions.gameAction.trading;

import soc.common.actions.gameAction.turns.AbstractTurnAction;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.turnPhase.TradingTurnPhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;

public abstract class AbstractTradeAction extends AbstractTurnAction
{
    private static final long serialVersionUID = 7078710036730939921L;

    @Override
    public String getToDoMessage()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isAllowed(TurnPhase turnPhase)
    {
        return turnPhase instanceof TradingTurnPhase;
    }

    @Override
    public boolean isAllowed(GamePhase gamePhase)
    {
        return gamePhase.isPlayTurns();
    }
}
