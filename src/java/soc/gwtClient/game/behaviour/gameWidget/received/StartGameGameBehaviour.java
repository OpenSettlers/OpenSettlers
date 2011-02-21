package soc.gwtClient.game.behaviour.gameWidget.received;

import soc.gwtClient.game.behaviour.gameBoard.DisabledMap;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;

public class StartGameGameBehaviour implements ReceiveGameBehaviour
{
    DisabledMap disabledMap = new DisabledMap();
    GameWidget gameWidget;

    public StartGameGameBehaviour(GameWidget gameWidget)
    {
        super();
        this.gameWidget = gameWidget;
    }

    @Override
    public void finish()
    {
        disabledMap.setNeutral(gameWidget.getBoardVisualWidget()
                .getBoardVisual());
    }

    @Override
    public void start(GameWidget gameWidget)
    {
        disabledMap.start(gameWidget.getBoardVisualWidget().getBoardVisual());
    }

    @Override
    public boolean endsManually()
    {
        return false;
    }

}
