package soc.gwtClient.client.game;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.game.Player;

public abstract class AbstractActionWidget implements IActionWidget
{
    protected Player player;
    protected IGamePanel gamePanel;
    protected ComplexPanel rootPanel;

    public AbstractActionWidget(IGamePanel gamePanel, Player player)
    {
        this.player=player;
        this.gamePanel = gamePanel;
    }

    @Override
    public Player getPlayer()
    {
        return player;
    }

}
