package soc.gwtClient.game.behaviour.gamePanel;

import soc.common.actions.gameAction.turnActions.standard.PlaceRobber;
import soc.gwtClient.game.behaviour.gameBoard.PlaceRobberBehaviour;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;

public class MoveRobberGameBehaviour implements GameBehaviour
{
    private PlaceRobber placeRobber;
    private PlaceRobberBehaviour placeRobberBehaviour;
    private GameWidget gamePanel;

    public MoveRobberGameBehaviour(PlaceRobber placeRobber, GameWidget gamePanel)
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
    public void start(GameWidget gamePanel)
    {
        gamePanel.blockUI();
        gamePanel.getBoardVisualWidget().getBoardVisual().setBehaviour(
                placeRobberBehaviour);
    }

    public void pickedRobberSpot(PlaceRobberBehaviour placeRobberBehaviour)
    {
        gamePanel.unBlockUI();
        placeRobberBehaviour.setNeutral(gamePanel.getBoardVisualWidget()
                .getBoardVisual());
        gamePanel.getBoardVisualWidget().getBoardVisual().stopBehaviour();
        gamePanel.sendAction(placeRobber);
    }

    public PlaceRobber getPlaceRobber()
    {
        return placeRobber;
    }
}
