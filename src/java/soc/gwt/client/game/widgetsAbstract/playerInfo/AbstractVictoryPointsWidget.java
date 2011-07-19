package soc.gwt.client.game.widgetsAbstract.playerInfo;

import soc.common.game.player.GamePlayer;
import soc.common.views.widgetsInterface.generic.ToolTip;
import soc.common.views.widgetsInterface.main.GameWidget;
import soc.common.views.widgetsInterface.payerInfo.VictoryPointAmountWidget;
import soc.gwt.client.game.widgetsBitmap.tooltips.VictoryPointsToolTip;

import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractVictoryPointsWidget implements
        VictoryPointAmountWidget, MouseOutHandler, MouseOverHandler
{
    protected ComplexPanel rootPanel;
    protected GamePlayer player;
    protected GameWidget gameWidget;
    protected ToolTip vpToolTip;

    public AbstractVictoryPointsWidget(GameWidget gameWidget, GamePlayer player)
    {
        this.gameWidget = gameWidget;
        this.player = player;

        rootPanel = createRootPanel();
        vpToolTip = new VictoryPointsToolTip(gameWidget, player);

        rootPanel.addDomHandler(this, MouseOutEvent.getType());
        rootPanel.addDomHandler(this, MouseOverEvent.getType());
    }

    @Override
    public ComplexPanel createRootPanel()
    {
        return new VerticalPanel();
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }

    @Override
    public void onMouseOut(MouseOutEvent event)
    {
        gameWidget.getToolTipManager().hideToolTip(vpToolTip);
    }

    @Override
    public void onMouseOver(MouseOverEvent event)
    {
        gameWidget.getToolTipManager().showToolTip(vpToolTip);
    }
}