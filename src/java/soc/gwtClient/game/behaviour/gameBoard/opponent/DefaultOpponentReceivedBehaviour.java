package soc.gwtClient.game.behaviour.gameBoard.opponent;

import soc.common.actions.gameAction.GameAction;
import soc.common.ui.ActionDetailWidgetFactory;
import soc.gwtClient.game.behaviour.gameBoard.received.ReceiveGameBehaviour;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.game.widgetsInterface.playerDetail.ActionDetailWidget;

public class DefaultOpponentReceivedBehaviour implements ReceiveGameBehaviour
{
    private GameAction gameAction;
    private ActionDetailWidget playerDetailWidget;
    private GameWidget gamePanel;

    public DefaultOpponentReceivedBehaviour(GameWidget gamePanel,
            GameAction gameAction)
    {
        super();
        this.gamePanel = gamePanel;
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
        gamePanel.getDetailContainerManager().hideCurrentWidget();
    }

    @Override
    public void start(GameWidget gamePanel)
    {
        ActionDetailWidgetFactory factory = gamePanel.getClientFactory()
                .getActionDetailWidgetFactory();
        ActionDetailWidget actionDetailWidget = gameAction
                .createActionDetailWidget(factory);
        if (actionDetailWidget != null)
            gamePanel.getDetailContainerManager().showActionWidget(
                    actionDetailWidget);
    }
}