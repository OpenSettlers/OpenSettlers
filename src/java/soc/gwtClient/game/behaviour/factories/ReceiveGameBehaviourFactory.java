package soc.gwtClient.game.behaviour.factories;

import soc.common.actions.gameAction.GameAction;
import soc.gwtClient.game.behaviour.received.ReceiveGameBehaviour;

public interface ReceiveGameBehaviourFactory
{
    public ReceiveGameBehaviour createBehaviour(GameAction turnAction);
}
