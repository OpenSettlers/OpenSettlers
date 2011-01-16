package soc.gwtClient.game.behaviour;

import soc.common.actions.gameAction.MessageFromServer;
import soc.gwtClient.game.abstractWidgets.GamePanel;

public class ErrorReceivedGameBehaviour implements GameBehaviour
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

}
