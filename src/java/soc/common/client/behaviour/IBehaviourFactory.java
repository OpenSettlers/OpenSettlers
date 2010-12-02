package soc.common.client.behaviour;

import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.client.behaviour.game.IGameBehaviour;

/*
 * Creates GameVisual behavior based on a TurnAction 
 */
public interface IBehaviourFactory
{
    public IGameBehaviour createBehaviour(TurnAction turnAction);
}
