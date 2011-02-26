package soc.common.views.behaviour.gameWidget.received;

import soc.common.actions.gameAction.standard.RollDice;
import soc.common.views.behaviour.gameBoard.RollDiceBehaviour;
import soc.common.views.widgetsInterface.actions.ActionDetailWidgetFactory;
import soc.common.views.widgetsInterface.main.GameWidget;
import soc.common.views.widgetsInterface.payerInfo.ActionDetailWidget;

public class RollDiceResult implements ReceiveGameBehaviour
{
    private RollDice rolledDice;
    private RollDiceBehaviour rollDiceBehaviour;
    private GameWidget gameWidget;

    public RollDiceResult(GameWidget gameWidget, RollDice rolledDice)
    {
        super();
        this.gameWidget = gameWidget;
        this.rolledDice = rolledDice;

        rollDiceBehaviour = new RollDiceBehaviour(rolledDice);
    }

    @Override
    public void start(GameWidget gameWidget)
    {
        if (rolledDice.isRobberRolled())
        {
            if (rolledDice.getLooserPlayers().size() > 0)
            {
                gameWidget.getLooseCardsDialog().update(rolledDice, this);
            }
            else
            {
                // Rolled 7, nothing to see here, move along.
                gameWidget.doneReceiveBehaviour();
            }
        }
        else
        {
            // Show the hexes which have been rolled
            gameWidget.getBoardVisualWidget().getBoardVisual().setBehaviour(
                    rollDiceBehaviour);

            // Grab an ActionDetailWidget
            ActionDetailWidgetFactory factory = gameWidget.getClientFactory()
                    .getActionDetailWidgetFactory();
            ActionDetailWidget widget = rolledDice
                    .createActionDetailWidget(factory);

            // Show the ActionDetailWidget
            gameWidget.getDetailContainerManager().showActionWidget(widget);

            gameWidget.getResourcesGainedWidget().update(this);
            gameWidget.getActionsWidget().setEnabled(false);
        }
    }

    @Override
    public void finish()
    {
    }

    public void doneLoosingCards()
    {
        gameWidget.doneReceiveBehaviour();
    }

    public void doneResources()
    {
        rollDiceBehaviour.setNeutral(gameWidget.getBoardVisualWidget()
                .getBoardVisual());
        gameWidget.getDetailContainerManager().hideCurrentWidget();
        gameWidget.getActionsWidget().setEnabled(true);
        gameWidget.doneReceiveBehaviour();
    }

    @Override
    public boolean endsManually()
    {
        return true;
    }
}
