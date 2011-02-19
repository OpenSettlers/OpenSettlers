package soc.common.game.gamePhase.turnPhase;

import java.io.Serializable;

import soc.common.actions.gameAction.GameAction;
import soc.common.game.Game;
import soc.common.utils.ClassUtils;

public abstract class TurnPhase implements Serializable
{
    private static final long serialVersionUID = 6519636332497912105L;

    public TurnPhase next()
    {
        throw new RuntimeException();
    }

    public TurnPhase processAction(GameAction action, Game game)
    {
        throw new RuntimeException();
    }

    public boolean isAllowed(GameAction action)
    {
        return action.isAllowed(this);
    }

    public String getName()
    {
        return ClassUtils.getSimpleClassName(this.getClass().getName());
    }

    public boolean isBeforeDiceRoll()
    {
        return this.getClass() == BeforeDiceRollTurnPhase.class;
    }

    public boolean isDiceRoll()
    {
        return this.getClass() == RollDiceTurnPhase.class;
    }

    public boolean isTrading()
    {
        return this.getClass() == TradingTurnPhase.class;
    }

    public boolean isBuilding()
    {
        return this.getClass() == BuildingTurnPhase.class;
    }

    public abstract String getMessage();
}
