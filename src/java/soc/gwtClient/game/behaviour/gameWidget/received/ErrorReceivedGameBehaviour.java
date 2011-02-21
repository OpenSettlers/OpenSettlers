package soc.gwtClient.game.behaviour.gameWidget.received;

import soc.common.actions.gameAction.MessageFromServer;
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
    public void start(GameWidget gameWidget)
    {
        gameWidget.getDebugPanel().addError(messageFromServer);
    }

    @Override
    public boolean endsManually()
    {
        return false;
    }

}
