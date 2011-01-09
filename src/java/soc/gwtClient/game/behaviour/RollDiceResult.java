package soc.gwtClient.game.behaviour;

import soc.common.actions.gameAction.turnActions.standard.PlaceRobber;
import soc.common.actions.gameAction.turnActions.standard.RobPlayer;
import soc.common.actions.gameAction.turnActions.standard.RollDice;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.visuals.behaviour.gameBoard.PlaceRobberBehaviour;
import soc.gwtClient.visuals.behaviour.gameBoard.RollDiceBehaviour;

public class RollDiceResult implements GameBehaviour
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
            // Create a new PlaceRobber action
            placeRobber = new PlaceRobber();
            placeRobber.setPlayer(gamePanel.getPlayingPlayer());

            // Create a new place robber behaviour and start it
            placeRobberBehaviour = new PlaceRobberBehaviour(this);
            placeRobberBehaviour.start(gamePanel.getGameBoardVisual());
        }
        else
        {
            // Show the hexes which have been rolled
            rollDiceBehaviour.start(gamePanel.getGameBoardVisual());

            // Ensure the resources gained widget shows current results
            gamePanel.getResourcesGainedWidget().update(this);
        }
    }

    @Override
    public void finish()
    {

    }

    public void robbedPlayer(RobPlayer robplayer)
    {
        gamePanel.startAction(robplayer);
    }

    public void doneResources()
    {
        rollDiceBehaviour.setNeutral(gamePanel.getGameBoardVisual());
    }

    public void pickedRobberSpot(PlaceRobberBehaviour placeRobberBehaviour)
    {
        placeRobberBehaviour.setNeutral(gamePanel.getGameBoardVisual());
        gamePanel.startAction(placeRobberBehaviour.getPlaceRobber());
        gamePanel.getStealCardWidget().update(this);
    }
}
