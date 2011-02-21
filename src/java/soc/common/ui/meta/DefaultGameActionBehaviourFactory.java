package soc.common.ui.meta;

import soc.gwtClient.game.behaviour.gameBoard.factories.GameBehaviourFactory;
import soc.gwtClient.game.behaviour.gameBoard.factories.NextActionGameBehaviourFactory;
import soc.gwtClient.game.behaviour.gameBoard.factories.OpponentBehaviourFactory;
import soc.gwtClient.game.behaviour.gameBoard.factories.ReceiveGameBehaviourFactory;
import soc.gwtClient.game.behaviour.gameBoard.factories.ReceivedActionGameBehaviourFactory;
import soc.gwtClient.game.behaviour.gameBoard.factories.SendGameBehaviourFactory;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;

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
