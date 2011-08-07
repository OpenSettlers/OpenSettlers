package org.soc.common.views.behaviour.gameWidget.beforeSend;

import org.soc.common.actions.gameAction.standard.RobPlayer;
import org.soc.common.views.behaviour.gameWidget.GameBehaviour;
import org.soc.common.views.widgetsInterface.main.GameWidget;

public class RobPlayerGameBehaviour implements GameBehaviour
{
    private GameWidget gameWidget;

    public RobPlayerGameBehaviour(GameWidget gameWidget)
    {
        super();
        this.gameWidget = gameWidget;
    }

    @Override
    public void finish()
    {
    }

    @Override
    public void start(GameWidget gameWidget)
    {
        gameWidget.blockUI();
        gameWidget.getStealCardWidget().update(this);
    }

    public void robbedPlayer(RobPlayer robplayer)
    {
        gameWidget.unBlockUI();
        gameWidget.sendAction(robplayer);
    }
}
