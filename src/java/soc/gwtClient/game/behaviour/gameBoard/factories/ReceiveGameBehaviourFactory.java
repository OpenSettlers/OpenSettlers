package soc.gwtClient.game.behaviour.gameBoard.factories;

import soc.common.actions.gameAction.GameAction;
import soc.gwtClient.game.behaviour.gameBoard.received.ReceiveGameBehaviour;

public interface ReceiveGameBehaviourFactory
{
    public ReceiveGameBehaviour createBehaviour(GameAction turnAction);
}
