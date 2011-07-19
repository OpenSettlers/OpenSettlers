package soc.gwt.client.game.widgetsAbstract.toolTips;

import soc.common.game.player.GamePlayer;
import soc.common.views.widgetsInterface.main.GameWidget;

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
