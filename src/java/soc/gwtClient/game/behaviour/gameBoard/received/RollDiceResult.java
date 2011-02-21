package soc.gwtClient.game.behaviour.gameBoard.received;

import soc.common.actions.gameAction.standard.RollDice;
import soc.gwtClient.game.behaviour.gameBoard.RollDiceBehaviour;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.game.widgetsInterface.playerDetail.ActionDetailWidget;

public class RollDiceResult implements ReceiveGameBehaviour
{
    private RollDice rolledDice;
    private RollDiceBehaviour rollDiceBehaviour;
    private GameWidget gamePanel;

    public RollDiceResult(GameWidget gamePanel, RollDice rolledDice)
    {
        super();
        this.gamePanel = gamePanel;
        this.rolledDice = rolledDice;

        rollDiceBehaviour = new RollDiceBehaviour(rolledDice);
    }

    @Override
    public void start(GameWidget gamePanel)
    {
        if (rolledDice.isRobberRolled())
        {
            if (rolledDice.getLooserPlayers().size() > 0)
            {
                gamePanel.getLooseCardsDialog().update(rolledDice, this);
            }
            else
            {
                // Rolled 7, nothing to see here, move along.
                gamePanel.doneReceiveBehaviour();
            }
        }
        else
        {
            // Show the hexes which have been rolled
            gamePanel.getBoardVisualWidget().getBoardVisual().setBehaviour(
                    rollDiceBehaviour);

            ActionDetailWidget widget = rolledDice.createActionDetailWidget();

            gamePanel.getDetailContainerManager().showActionWidget(widget);

            gamePanel.getResourcesGainedWidget().update(this);
            gamePanel.getActionsWidget().setEnabled(false);
        }
    }

    @Override
    public void finish()
    {
    }

    public void doneLoosingCards()
    {
        gamePanel.doneReceiveBehaviour();
    }

    public void doneResources()
    {
        rollDiceBehaviour.setNeutral(gamePanel.getBoardVisualWidget()
                .getBoardVisual());
        gamePanel.getDetailContainerManager().hideCurrentWidget();
        gamePanel.getActionsWidget().setEnabled(true);
        gamePanel.doneReceiveBehaviour();
    }

    @Override
    public boolean endsManually()
    {
        return true;
    }
}
