package soc.gwtClient.game.behaviour;

import soc.common.actions.gameAction.MessageFromServer;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.game.behaviour.received.ReceiveGameBehaviour;

public class ErrorReceivedGameBehaviour implements ReceiveGameBehaviour
{
    private MessageFromServer messageFromServer;

    public ErrorReceivedGameBehaviour(MessageFromServer messageFromServer)
    {
        super();
        this.messageFromServer = messageFromServer;
    }

    @Override
    public void finish()
    {

    }

    @Override
    public void start(GamePanel gamePanel)
    {
        gamePanel.getDebugPanel().addError(messageFromServer);
    }

    @Override
    public boolean endsManually()
    {
        return false;
    }

}
