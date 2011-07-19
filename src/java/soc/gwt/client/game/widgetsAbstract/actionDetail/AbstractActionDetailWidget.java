package soc.gwt.client.game.widgetsAbstract.actionDetail;

import soc.common.game.player.GamePlayer;
import soc.common.views.widgetsInterface.main.GameWidget;
import soc.common.views.widgetsInterface.payerInfo.ActionDetailWidget;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractActionDetailWidget implements ActionDetailWidget
{
    protected GamePlayer player;
    protected HorizontalPanel rootPanel = new HorizontalPanel();
    protected GameWidget gameWidget;

    public AbstractActionDetailWidget(GameWidget gameWidget, GamePlayer player)
    {
        super();
        this.player = player;
        this.gameWidget = gameWidget;
        rootPanel.setHeight("2em");
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }
}
