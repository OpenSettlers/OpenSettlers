package org.soc.common.views.behaviour.gameWidget.beforeSend;

import org.soc.common.actions.gameAction.standard.PlaceRobber;
import org.soc.common.views.behaviour.gameBoard.PlaceRobberBehaviour;
import org.soc.common.views.behaviour.gameWidget.GameBehaviour;
import org.soc.common.views.widgetsInterface.main.GameWidget;

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
