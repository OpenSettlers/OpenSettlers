package soc.gwtClient.game.behaviour;

import soc.common.actions.gameAction.turnActions.TurnAction;

/*
 * Relates a TurnAction to a viewport GameBehaviour. 
 * Different variants will have different factories depending on the TurnActions
 * specific for that variant.
 */
public interface GameBehaviourFactory
{
    public GameBehaviour createBehaviour(TurnAction turnAction);
}
