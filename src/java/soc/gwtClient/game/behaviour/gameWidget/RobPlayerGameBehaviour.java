package soc.gwtClient.game.behaviour.gameWidget;

import soc.common.actions.gameAction.standard.RobPlayer;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;

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
