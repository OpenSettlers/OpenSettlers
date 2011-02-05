package soc.gwtClient.game.behaviour.received;

import soc.common.actions.gameAction.turnActions.standard.RollDice;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.visuals.behaviour.gameBoard.RollDiceBehaviour;

public class RollDiceResult implements ReceiveGameBehaviour
{
    private RollDice rolledDice;
    private RollDiceBehaviour rollDiceBehaviour;
    private GamePanel gamePanel;

    public RollDiceResult(GamePanel gamePanel, RollDice rolledDice)
    {
        super();
        this.gamePanel = gamePanel;
        this.rolledDice = rolledDice;

        rollDiceBehaviour = new RollDiceBehaviour(rolledDice);
    }

    @Override
    public void start(GamePanel gamePanel)
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
            gamePanel.getGameBoardVisual().setBehaviour(rollDiceBehaviour);

            gamePanel.getDetailContainerManager().showResourcesGained(
                    rolledDice);

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
        rollDiceBehaviour.setNeutral(gamePanel.getGameBoardVisual());
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
