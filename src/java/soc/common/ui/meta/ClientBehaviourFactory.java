package soc.common.ui.meta;

import soc.gwtClient.game.behaviour.gameBoard.factories.GameBehaviourFactory;
import soc.gwtClient.game.behaviour.gameBoard.factories.ReceiveGameBehaviourFactory;

public interface ClientBehaviourFactory
{
    public ReceiveGameBehaviourFactory getOpponentReceiveBehaviourFactory();

    public ReceiveGameBehaviourFactory getReceiveBehaviourFactory();;

    public GameBehaviourFactory getSendBehaviourFactory();

    public GameBehaviourFactory getNextActionBehaviourFactory();
}
