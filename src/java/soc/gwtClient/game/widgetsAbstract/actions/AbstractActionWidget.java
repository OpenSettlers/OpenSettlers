package soc.gwtClient.game.widgetsAbstract.actions;

import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.widgetsInterface.actions.ActionWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;

import com.google.gwt.user.client.ui.ComplexPanel;

public abstract class AbstractActionWidget implements ActionWidget
{
    protected GamePlayer player;
    protected GameWidget gamePanel;
    protected ComplexPanel rootPanel;
    protected boolean onTurn;
    protected boolean enabled;

    protected abstract void updateEnabled();

    public AbstractActionWidget(GameWidget gamePanel, GamePlayer player)
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
