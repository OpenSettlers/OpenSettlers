package soc.gwtClient.game.behaviour.opponent;

import soc.common.actions.gameAction.GameAction;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.game.abstractWidgets.PlayerDetailWidget;
import soc.gwtClient.game.behaviour.received.ReceiveGameBehaviour;

public class DefaultOpponentReceivedBehaviour implements ReceiveGameBehaviour
{
    private GameAction gameAction;
    private PlayerDetailWidget playerDetailWidget;
    private GamePanel gamePanel;

    public DefaultOpponentReceivedBehaviour(GamePanel gamePanel,
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
    public void start(GamePanel gamePanel)
    {
        gamePanel.getDetailContainerManager().showActionWidget(gameAction);
    }

}
