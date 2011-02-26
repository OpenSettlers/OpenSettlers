package soc.common.views.behaviour.gameWidget.beforeSend;

import soc.common.actions.gameAction.standard.RobPlayer;
import soc.common.views.behaviour.gameWidget.GameBehaviour;
import soc.common.views.widgetsInterface.main.GameWidget;

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
