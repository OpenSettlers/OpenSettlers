package org.soc.gwt.client.game.widgetsAbstract.playerInfo;

import org.soc.common.game.GamePlayer;
import org.soc.common.views.widgetsInterface.generic.ToolTip;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.common.views.widgetsInterface.playerInfo.VictoryPointAmountWidget;
import org.soc.gwt.client.game.widgetsBitmap.tooltips.VictoryPointsToolTip;

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
        gameWidget.toolTipManager().hide(vpToolTip);
    }

    @Override
    public void onMouseOver(MouseOverEvent event)
    {
        gameWidget.toolTipManager().show(vpToolTip);
    }
}