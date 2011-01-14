package soc.gwtClient.game.behaviour;

import soc.common.actions.gameAction.turnActions.standard.PlaceRobber;
import soc.common.actions.gameAction.turnActions.standard.RobPlayer;
import soc.common.actions.gameAction.turnActions.standard.RollDice;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.visuals.behaviour.gameBoard.PlaceRobberBehaviour;
import soc.gwtClient.visuals.behaviour.gameBoard.RollDiceBehaviour;

public class RollDiceResult implements GameBehaviour, SoldierPlayBehaviour
{
    private RollDice rolledDice;
    private RollDiceBehaviour rollDiceBehaviour;
    private PlaceRobberBehaviour placeRobberBehaviour;
    private PlaceRobber placeRobber;
    private GamePanel gamePanel;

    public RollDiceResult(RollDice rolledDice)
    {
        super();
        this.rolledDice = rolledDice;

        rollDiceBehaviour = new RollDiceBehaviour(rolledDice);
    }

    /**
     * @return the rolledDice
     */
    public RollDice getRolledDice()
    {
        return rolledDice;
    }

    /**
     * @return the placeRobber
     */
    public PlaceRobber getPlaceRobber()
    {
        return placeRobber;
    }

    @Override
    public void start(GamePanel gamePanel)
    {
        this.gamePanel = gamePanel;
        if (rolledDice.isRobberRolled())
        {
            if (rolledDice.getLooserPlayers().size() > 0)
            {
                gamePanel.getLooseCardsDialog().update(rolledDice, this);
            }
            else
            {
                startRobbing();
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

    private void startRobbing()
    {
        // Create a new PlaceRobber action
        placeRobber = new PlaceRobber();
        placeRobber.setPlayer(gamePanel.getPlayingPlayer());

        // Create a new place robber behaviour and start it
        placeRobberBehaviour = new PlaceRobberBehaviour(this);
        gamePanel.getGameBoardVisual().setBehaviour(placeRobberBehaviour);
    }

    @Override
    public void finish()
    {

    }

    public void robbedPlayer(RobPlayer robplayer)
    {
        gamePanel.sendAction(robplayer);
        gamePanel.getActionsWidget().setEnabled(true);
    }

    public void doneResources()
    {
        rollDiceBehaviour.setNeutral(gamePanel.getGameBoardVisual());
        gamePanel.getDetailContainerManager().hideAll();
        gamePanel.getActionsWidget().setEnabled(true);
    }

    public void pickedRobberSpot(PlaceRobberBehaviour placeRobberBehaviour)
    {
        // gamePanel.getGameBoardVisual().setBehaviour(null);
        placeRobberBehaviour.setNeutral(gamePanel.getGameBoardVisual());
        gamePanel.startAction(placeRobberBehaviour.getPlaceRobber());
        gamePanel.getStealCardWidget().update(this,
                placeRobberBehaviour.getPlaceRobber(),
                gamePanel.getPlayingPlayer());
    }

    public void doneLoosingCards()
    {
        startRobbing();
    }
}
