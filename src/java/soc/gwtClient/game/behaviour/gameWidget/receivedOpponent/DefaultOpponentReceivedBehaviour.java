package soc.gwtClient.game.behaviour.gameWidget.receivedOpponent;

import soc.common.actions.gameAction.GameAction;
import soc.gwtClient.game.behaviour.gameWidget.received.ReceiveGameBehaviour;
import soc.gwtClient.game.widgetsInterface.actions.ActionDetailWidgetFactory;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.game.widgetsInterface.playerInfo.ActionDetailWidget;

public class DefaultOpponentReceivedBehaviour implements ReceiveGameBehaviour
{
    private GameAction gameAction;
    private ActionDetailWidget playerDetailWidget;
    private GameWidget gameWidget;

    public DefaultOpponentReceivedBehaviour(GameWidget gameWidget,
            GameAction gameAction)
    {
        super();
        this.gameWidget = gameWidget;
        this.gameAction = gameAction;
    }

    @Override
    public boolean endsManually()
    {
        return false;
    }

    @Override
    public void finish()
    {
        gameWidget.getDetailContainerManager().hideCurrentWidget();
    }

    @Override
    public void start(GameWidget gameWidget)
    {
        ActionDetailWidgetFactory factory = gameWidget.getClientFactory()
                .getActionDetailWidgetFactory();
        ActionDetailWidget actionDetailWidget = gameAction
                .createActionDetailWidget(factory);
        if (actionDetailWidget != null)
            gameWidget.getDetailContainerManager().showActionWidget(
                    actionDetailWidget);
    }
}