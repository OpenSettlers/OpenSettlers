package soc.common.game.gamePhase.turnPhase;

import soc.common.actions.gameAction.GameAction;
import soc.common.game.Game;
import soc.common.utils.ClassUtils;

public abstract class TurnPhase
{
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

    public abstract String getMessage();
}
