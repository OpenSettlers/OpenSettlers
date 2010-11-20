package soc.common.game.gamePhase.turnPhase;

import java.util.ArrayList;
import java.util.List;

import soc.common.actions.gameAction.GameAction;
import soc.common.game.Game;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public abstract class TurnPhase
{
    public TurnPhase next()
    {
        throw new NotImplementedException();
    }
    
    public TurnPhase processAction(GameAction action, Game game)
    {
        throw new NotImplementedException();
    }
    
    public boolean isAllowed(GameAction action)
    {
        return action.isAllowed(this);
    }
}
