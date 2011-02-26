package soc.common.views.behaviour.gameWidget.received;

import soc.common.actions.gameAction.meta.MessageFromServer;
import soc.common.views.widgetsInterface.main.GameWidget;

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
