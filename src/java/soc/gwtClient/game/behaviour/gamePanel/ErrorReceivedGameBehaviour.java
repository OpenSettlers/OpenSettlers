package soc.gwtClient.game.behaviour.gamePanel;

import soc.common.actions.gameAction.MessageFromServer;
import soc.gwtClient.game.behaviour.gameBoard.received.ReceiveGameBehaviour;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;

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
    public void start(GameWidget gamePanel)
    {
        gamePanel.getDebugPanel().addError(messageFromServer);
    }

    @Override
    public boolean endsManually()
    {
        return false;
    }

}
