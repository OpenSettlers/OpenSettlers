package org.soc.common.views.behaviour.gameWidget.received;

import org.soc.common.views.behaviour.gameBoard.DisabledMap;
import org.soc.common.views.widgetsInterface.main.GameWidget;

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
