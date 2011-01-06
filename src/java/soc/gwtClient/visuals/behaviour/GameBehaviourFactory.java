package soc.gwtClient.visuals.behaviour;

import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.game.Game;
import soc.gwtClient.visuals.behaviour.game.GameBehaviour;

/*
 * Relates a TurnAction to a viewport GameBehaviour. 
 * Different variants will have different factories depending on the TurnActions
 * specific for that variant.
 */
public interface GameBehaviourFactory
{
    public GameBehaviour createBehaviour(TurnAction turnAction, Game game);
}
