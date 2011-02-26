package soc.common.views.meta;

import soc.common.views.behaviour.gameWidget.factories.GameBehaviourFactory;
import soc.common.views.behaviour.gameWidget.factories.NextActionGameBehaviourFactory;
import soc.common.views.behaviour.gameWidget.factories.OpponentBehaviourFactory;
import soc.common.views.behaviour.gameWidget.factories.ReceiveGameBehaviourFactory;
import soc.common.views.behaviour.gameWidget.factories.ReceivedActionGameBehaviourFactory;
import soc.common.views.behaviour.gameWidget.factories.SendGameBehaviourFactory;
import soc.common.views.widgetsInterface.main.GameWidget;

public class DefaultGameActionBehaviourFactory implements
        ClientBehaviourFactory
{
    private GameBehaviourFactory nextActionBehaviourFactory;
    private GameBehaviourFactory sendActionBehaviourFactory;
    private ReceiveGameBehaviourFactory opponentReceiveBehaviourFactory;
    private ReceiveGameBehaviourFactory receiveBehaviourFactory;
    private GameWidget gameWidget;

    public DefaultGameActionBehaviourFactory(GameWidget gameWidget)
    {
        super();
        this.gameWidget = gameWidget;

        nextActionBehaviourFactory = new NextActionGameBehaviourFactory(
                gameWidget);
        opponentReceiveBehaviourFactory = new OpponentBehaviourFactory(
                gameWidget);
        receiveBehaviourFactory = new ReceivedActionGameBehaviourFactory(
                gameWidget);
        sendActionBehaviourFactory = new SendGameBehaviourFactory(gameWidget);
    }

    @Override
    public GameBehaviourFactory getSendBehaviourFactory()
    {
        return sendActionBehaviourFactory;
    }

    @Override
    public GameBehaviourFactory getNextActionBehaviourFactory()
    {
        return nextActionBehaviourFactory;
    }

    @Override
    public ReceiveGameBehaviourFactory getOpponentReceiveBehaviourFactory()
    {
        return opponentReceiveBehaviourFactory;
    }

    @Override
    public ReceiveGameBehaviourFactory getReceiveBehaviourFactory()
    {
        return receiveBehaviourFactory;
    }

}
