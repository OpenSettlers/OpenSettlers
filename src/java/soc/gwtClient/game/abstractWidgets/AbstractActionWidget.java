package soc.gwtClient.game.abstractWidgets;

import soc.common.game.player.GamePlayer;

import com.google.gwt.user.client.ui.ComplexPanel;

public abstract class AbstractActionWidget implements ActionWidget
{
    protected GamePlayer player;
    protected GamePanel gamePanel;
    protected ComplexPanel rootPanel;
    protected boolean onTurn;
    protected boolean enabled;

    protected abstract void updateEnabled();

    public AbstractActionWidget(GamePanel gamePanel, GamePlayer player)
    {
        this.player = player;
        this.gamePanel = gamePanel;
    }

    @Override
    public GamePlayer getPlayer()
    {
        return player;
    }

    @Override
    public ActionWidget setEnabled(boolean enabled)
    {
        this.enabled = enabled;

        updateEnabled();

        return this;
    }
}
