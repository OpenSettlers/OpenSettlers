package soc.gwtClient.game.behaviour;

import soc.common.actions.gameAction.turnActions.standard.RobPlayer;
import soc.gwtClient.game.abstractWidgets.GamePanel;

public class RobPlayerGameBehaviour implements GameBehaviour
{
    private GamePanel gamePanel;

    public RobPlayerGameBehaviour(GamePanel gamePanel)
    {
        super();
        this.gamePanel = gamePanel;
    }

    @Override
    public void finish()
    {
    }

    @Override
    public void start(GamePanel gamePanel)
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
