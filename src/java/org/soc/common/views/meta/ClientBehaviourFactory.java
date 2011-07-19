package org.soc.common.views.meta;

import org.soc.common.views.behaviour.gameWidget.factories.GameBehaviourFactory;
import org.soc.common.views.behaviour.gameWidget.factories.ReceiveGameBehaviourFactory;

public interface ClientBehaviourFactory
{
    public ReceiveGameBehaviourFactory getOpponentReceiveBehaviourFactory();

    public ReceiveGameBehaviourFactory getReceiveBehaviourFactory();;

    public GameBehaviourFactory getSendBehaviourFactory();

    public GameBehaviourFactory getNextActionBehaviourFactory();
}
