package soc.common.client.behaviour;

import soc.common.actions.gameAction.turnActions.AbstractTurnAction;
import soc.common.client.behaviour.game.GameBehaviour;
import soc.common.game.Game;

/*
 * Relates a TurnAction to a viewport GameBehaviour. 
 * Different variants will have different factories depending on the TurnActions
 * specific for that variant.
 */
public interface GameBehaviourFactory
{
    public GameBehaviour createBehaviour(AbstractTurnAction turnAction, Game game);
}
