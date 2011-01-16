package soc.gwtClient.game.behaviour.factories;

import soc.common.actions.gameAction.GameAction;
import soc.gwtClient.game.behaviour.GameBehaviour;

/*
 * Relates a TurnAction to a viewport GameBehaviour. 
 * Different variants will have different factories depending on the TurnActions
 * specific for that variant.
 */
public interface GameBehaviourFactory
{
    public GameBehaviour createBehaviour(GameAction gameAction);
}
