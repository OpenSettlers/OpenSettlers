package soc.gwtClient.game.behaviour;

import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.game.behaviour.received.ReceiveGameBehaviour;
import soc.gwtClient.visuals.behaviour.gameBoard.DisabledMap;

public class StartGameGameBehaviour implements ReceiveGameBehaviour
{
    DisabledMap disabledMap = new DisabledMap();
    GamePanel gamePanel;

    public StartGameGameBehaviour(GamePanel gamePanel)
    {
        super();
        this.gamePanel = gamePanel;
    }

    @Override
    public void finish()
    {
        disabledMap.setNeutral(gamePanel.getGameBoardVisual());
    }

    @Override
    public void start(GamePanel gamePanel)
    {
        disabledMap.start(gamePanel.getGameBoardVisual());
    }

    @Override
    public boolean endsManually()
    {
        return false;
    }

}
