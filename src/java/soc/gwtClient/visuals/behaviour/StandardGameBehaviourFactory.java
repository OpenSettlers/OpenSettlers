package soc.gwtClient.visuals.behaviour;

import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.actions.gameAction.turnActions.standard.BuildCity;
import soc.common.actions.gameAction.turnActions.standard.BuildRoad;
import soc.common.actions.gameAction.turnActions.standard.BuildTown;
import soc.common.actions.gameAction.turnActions.standard.PlaceRobber;
import soc.common.game.Game;
import soc.gwtClient.visuals.behaviour.game.BuildCityBehaviour;
import soc.gwtClient.visuals.behaviour.game.BuildRoadBehaviour;
import soc.gwtClient.visuals.behaviour.game.BuildTownBehaviour;
import soc.gwtClient.visuals.behaviour.game.GameBehaviour;
import soc.gwtClient.visuals.behaviour.game.PlaceRobberBehaviour;

/*
 * Viewport GameBehaviour factory for a TurnAction using standard ruleset
 */
public class StandardGameBehaviourFactory implements GameBehaviourFactory
{
    @Override
    public GameBehaviour createBehaviour(TurnAction turnAction, Game game)
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
