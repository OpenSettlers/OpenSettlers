package soc.gwtClient.game.abstractWidgets;

import soc.common.game.player.GamePlayer;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractPlayerDetailWidget implements PlayerDetailWidget
{
    protected GamePlayer player;
    protected HorizontalPanel rootPanel = new HorizontalPanel();
    protected GamePanel gamePanel;

    public AbstractPlayerDetailWidget(GamePanel gamePanel, GamePlayer player)
    {
        super();
        this.player = player;
        this.gamePanel = gamePanel;
        rootPanel.setHeight("2em");
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }
}
