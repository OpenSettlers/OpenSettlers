package soc.common.views.behaviour.gameWidget.receivedOpponent;

import soc.common.actions.gameAction.GameAction;
import soc.common.views.behaviour.gameWidget.received.ReceiveGameBehaviour;
import soc.common.views.widgetsInterface.actions.ActionDetailWidgetFactory;
import soc.common.views.widgetsInterface.main.GameWidget;
import soc.common.views.widgetsInterface.payerInfo.ActionDetailWidget;

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