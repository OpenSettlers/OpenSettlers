package soc.common.views.meta;

import soc.common.views.behaviour.gameWidget.factories.GameBehaviourFactory;
import soc.common.views.behaviour.gameWidget.factories.ReceiveGameBehaviourFactory;

public interface ClientBehaviourFactory
{
    public ReceiveGameBehaviourFactory getOpponentReceiveBehaviourFactory();

    public ReceiveGameBehaviourFactory getReceiveBehaviourFactory();;

    public GameBehaviourFactory getSendBehaviourFactory();

    public GameBehaviourFactory getNextActionBehaviourFactory();
}
