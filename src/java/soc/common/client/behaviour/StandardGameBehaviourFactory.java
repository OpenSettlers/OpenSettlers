package soc.common.client.behaviour;

import soc.common.actions.gameAction.turnActions.AbstractTurnAction;
import soc.common.actions.gameAction.turnActions.standard.BuildCity;
import soc.common.actions.gameAction.turnActions.standard.BuildRoad;
import soc.common.actions.gameAction.turnActions.standard.BuildTown;
import soc.common.actions.gameAction.turnActions.standard.PlaceRobber;
import soc.common.client.behaviour.game.BuildCityBehaviour;
import soc.common.client.behaviour.game.BuildRoadBehaviour;
import soc.common.client.behaviour.game.BuildTownBehaviour;
import soc.common.client.behaviour.game.IGameBehaviour;
import soc.common.client.behaviour.game.PlaceRobberBehaviour;
import soc.common.game.Game;

/*
 * Viewport GameBehaviour factory for a TurnAction using standard ruleset
 */
public class StandardGameBehaviourFactory implements IGameBehaviourFactory
{
    @Override
    public IGameBehaviour createBehaviour(AbstractTurnAction turnAction,
            Game game)
    {
        if (turnAction instanceof BuildRoad)
        {
            return new BuildRoadBehaviour((BuildRoad) turnAction);
        }

        if (turnAction instanceof BuildCity)
        {
            return new BuildCityBehaviour((BuildCity) turnAction);
        }

        if (turnAction instanceof BuildTown)
        {
            return new BuildTownBehaviour((BuildTown) turnAction);
        }

        if (turnAction instanceof PlaceRobber)
        {
            return new PlaceRobberBehaviour((PlaceRobber) turnAction, game);
        }

        return null;
    }
}
