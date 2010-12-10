package soc.common.client.behaviour;

import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.actions.gameAction.turnActions.standard.*;
import soc.common.client.behaviour.game.BuildCityBehaviour;
import soc.common.client.behaviour.game.BuildRoadBehaviour;
import soc.common.client.behaviour.game.BuildTownBehaviour;
import soc.common.client.behaviour.game.IGameBehaviour;
import soc.common.client.behaviour.game.PlaceRobberBehaviour;
import soc.common.game.Game;

public class GameBehaviourFactory implements IBehaviourFactory
{

    @Override
    public IGameBehaviour createBehaviour(TurnAction turnAction, Game game)
    {
        if (turnAction instanceof BuildRoad)
        {
            return new BuildRoadBehaviour((BuildRoad)turnAction);
        }
        
        if (turnAction instanceof BuildCity)
        {
            return new BuildCityBehaviour((BuildCity)turnAction);
        }
        
        if (turnAction instanceof BuildTown)
        {
            return new BuildTownBehaviour((BuildTown)turnAction);
        }
        
        if (turnAction instanceof PlaceRobber)
        {
            return new PlaceRobberBehaviour((PlaceRobber)turnAction, game);
        }
        
        return null;
    }
}