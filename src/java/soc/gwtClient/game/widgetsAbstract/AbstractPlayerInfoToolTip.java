package soc.gwtClient.game.widgetsAbstract;

import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;

public abstract class AbstractPlayerInfoToolTip extends AbstractToolTip
{
    protected Panel rootPanel = new HorizontalPanel();
    protected GamePlayer player;

    public AbstractPlayerInfoToolTip(GameWidget gameWidget, GamePlayer player)
    {
        super();
        this.player = player;
        add(rootPanel);
    }
}