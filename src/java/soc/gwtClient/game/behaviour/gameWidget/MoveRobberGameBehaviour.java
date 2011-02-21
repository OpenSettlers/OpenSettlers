package soc.gwtClient.game.behaviour.gameWidget;

import soc.common.actions.gameAction.standard.PlaceRobber;
import soc.gwtClient.game.behaviour.gameBoard.PlaceRobberBehaviour;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;

public class MoveRobberGameBehaviour implements GameBehaviour
{
    private PlaceRobber placeRobber;
    private PlaceRobberBehaviour placeRobberBehaviour;
    private GameWidget gameWidget;

    public MoveRobberGameBehaviour(GameWidget gameWidget, PlaceRobber placeRobber)
    {
        super();
        this.placeRobber = placeRobber;
        this.gameWidget = gameWidget;

        placeRobberBehaviour = new PlaceRobberBehaviour(this);
    }

    @Override
    public void finish()
    {
    }

    @Override
    public void start(GameWidget gameWidget)
    {
        gameWidget.blockUI();
        gameWidget.getBoardVisualWidget().getBoardVisual().setBehaviour(
                placeRobberBehaviour);
    }

    public void pickedRobberSpot(PlaceRobberBehaviour placeRobberBehaviour)
    {
        gameWidget.unBlockUI();
        placeRobberBehaviour.setNeutral(gameWidget.getBoardVisualWidget()
                .getBoardVisual());
        gameWidget.getBoardVisualWidget().getBoardVisual().stopBehaviour();
        gameWidget.sendAction(placeRobber);
    }

    public PlaceRobber getPlaceRobber()
    {
        return placeRobber;
    }
}
