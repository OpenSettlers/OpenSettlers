package soc.common.client.behaviour;

import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.actions.gameAction.turnActions.standard.BuildRoad;
import soc.common.client.behaviour.game.BuildRoadBehaviour;
import soc.common.client.behaviour.game.IGameBehaviour;

public class GameBehaviourFactory implements IBehaviourFactory
{

    @Override
    public IGameBehaviour createBehaviour(TurnAction turnAction)
    {
        if (turnAction instanceof BuildRoad)
        {
            return new BuildRoadBehaviour((BuildRoad)turnAction);
        }
        
        return null;
    }

}
