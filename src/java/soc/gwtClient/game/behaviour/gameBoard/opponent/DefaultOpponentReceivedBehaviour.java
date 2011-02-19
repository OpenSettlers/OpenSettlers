package soc.gwtClient.game.behaviour.gameBoard.opponent;

import soc.common.actions.gameAction.GameAction;
import soc.gwtClient.game.behaviour.gameBoard.received.ReceiveGameBehaviour;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.game.widgetsInterface.playerDetail.PlayerDetailWidget;

public class DefaultOpponentReceivedBehaviour implements ReceiveGameBehaviour
{
    private GameAction gameAction;
    private PlayerDetailWidget playerDetailWidget;
    private GameWidget gamePanel;

    public DefaultOpponentReceivedBehaviour(GameWidget gamePanel,
            GameAction gameAction)
    {
        super();
        this.gamePanel = gamePanel;
        this.gameAction = gameAction;
    }

    @Override
    public boolean endsManually()
    {
        return false;
    }

    @Override
    public void finish()
    {
        gamePanel.getDetailContainerManager().hideMouseOverDetail(
                gameAction.getPlayer());
    }

    @Override
    public void start(GameWidget gamePanel)
    {
        gamePanel.getDetailContainerManager().showActionWidget(gameAction);
    }

}
