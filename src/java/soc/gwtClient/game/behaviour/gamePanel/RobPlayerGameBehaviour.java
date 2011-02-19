package soc.gwtClient.game.behaviour.gamePanel;

import soc.common.actions.gameAction.turnActions.standard.RobPlayer;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;

public class RobPlayerGameBehaviour implements GameBehaviour
{
    private GameWidget gamePanel;

    public RobPlayerGameBehaviour(GameWidget gamePanel)
    {
        super();
        this.gamePanel = gamePanel;
    }

    @Override
    public void finish()
    {
    }

    @Override
    public void start(GameWidget gamePanel)
    {
        gamePanel.blockUI();
        gamePanel.getStealCardWidget().update(this);
    }

    public void robbedPlayer(RobPlayer robplayer)
    {
        gamePanel.unBlockUI();
        gamePanel.sendAction(robplayer);
    }
}
