package soc.gwtClient.game.behaviour;

import soc.common.actions.gameAction.turnActions.standard.PlaceRobber;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.visuals.behaviour.gameBoard.PlaceRobberBehaviour;

public class MoveRobberGameBehaviour implements GameBehaviour
{
    private PlaceRobber placeRobber;
    private PlaceRobberBehaviour placeRobberBehaviour;
    private GamePanel gamePanel;

    public MoveRobberGameBehaviour(PlaceRobber placeRobber, GamePanel gamePanel)
    {
        super();
        this.placeRobber = placeRobber;
        this.gamePanel = gamePanel;

        placeRobberBehaviour = new PlaceRobberBehaviour(this);
    }

    @Override
    public void finish()
    {
    }

    @Override
    public void start(GamePanel gamePanel)
    {
        gamePanel.blockUI();
        gamePanel.getGameBoardVisual().setBehaviour(placeRobberBehaviour);
    }

    public void pickedRobberSpot(PlaceRobberBehaviour placeRobberBehaviour)
    {
        gamePanel.unBlockUI();
        placeRobberBehaviour.setNeutral(gamePanel.getGameBoardVisual());
        gamePanel.sendAction(placeRobber);
    }

    public PlaceRobber getPlaceRobber()
    {
        return placeRobber;
    }
}
