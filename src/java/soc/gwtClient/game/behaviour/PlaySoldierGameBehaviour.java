package soc.gwtClient.game.behaviour;

import soc.common.actions.gameAction.turnActions.standard.PlaceRobber;
import soc.common.actions.gameAction.turnActions.standard.PlayDevelopmentCard;
import soc.common.actions.gameAction.turnActions.standard.RobPlayer;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.visuals.behaviour.gameBoard.PlaceRobberBehaviour;

public class PlaySoldierGameBehaviour implements GameBehaviour,
        SoldierPlayBehaviour
{
    private PlayDevelopmentCard playDev;
    private GamePanel gamePanel;
    private PlaceRobberBehaviour placeRobberBehaviour;
    private PlaceRobber placeRobber;

    public PlaySoldierGameBehaviour(GamePanel gamePanel)
    {
        super();
        this.gamePanel = gamePanel;

        placeRobber = (PlaceRobber) new PlaceRobber().setPlayer(gamePanel
                .getPlayingPlayer());
    }

    @Override
    public void finish()
    {
    }

    @Override
    public void start(GamePanel gamePanel)
    {
        placeRobberBehaviour = new PlaceRobberBehaviour(this);
        gamePanel.getGameBoardVisual().setBehaviour(placeRobberBehaviour);
        gamePanel.getActionsWidget().setEnabled(false);
    }

    public PlaceRobber getPlaceRobber()
    {
        return placeRobber;
    }

    public void pickedRobberSpot(PlaceRobberBehaviour placeRobberBehaviour2)
    {
        placeRobberBehaviour.setNeutral(gamePanel.getGameBoardVisual());
        gamePanel.sendAction(placeRobberBehaviour.getPlaceRobber());
        gamePanel.getStealCardWidget().update(this,
                placeRobberBehaviour.getPlaceRobber(),
                gamePanel.getPlayingPlayer());
    }

    @Override
    public void robbedPlayer(RobPlayer robPlayer)
    {
        // A player is being robbed, send the action to the server
        gamePanel.sendAction(robPlayer);

        // Make sure UI is usable again
        gamePanel.getActionsWidget().setEnabled(true);
    }
}
