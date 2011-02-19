package soc.gwtClient.game.behaviour.gamePanel;

import soc.gwtClient.game.behaviour.gameBoard.DisabledMap;
import soc.gwtClient.game.behaviour.gameBoard.received.ReceiveGameBehaviour;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;

public class StartGameGameBehaviour implements ReceiveGameBehaviour
{
    DisabledMap disabledMap = new DisabledMap();
    GameWidget gamePanel;

    public StartGameGameBehaviour(GameWidget gamePanel)
    {
        super();
        this.gamePanel = gamePanel;
    }

    @Override
    public void finish()
    {
        disabledMap.setNeutral(gamePanel.getBoardVisualWidget()
                .getBoardVisual());
    }

    @Override
    public void start(GameWidget gamePanel)
    {
        disabledMap.start(gamePanel.getBoardVisualWidget().getBoardVisual());
    }

    @Override
    public boolean endsManually()
    {
        return false;
    }

}
