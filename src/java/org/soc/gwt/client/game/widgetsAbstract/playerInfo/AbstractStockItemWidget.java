package org.soc.gwt.client.game.widgetsAbstract.playerInfo;

import org.soc.common.game.player.GamePlayer;
import org.soc.common.views.widgetsInterface.generic.ToolTip;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.common.views.widgetsInterface.payerInfo.StockItemWidget;

import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractStockItemWidget implements StockItemWidget,
        MouseOverHandler, MouseOutHandler
{
    protected GamePlayer player;
    protected ComplexPanel rootPanel;
    protected GameWidget gameWidget;
    protected ToolTip toolTip;

    public AbstractStockItemWidget(GameWidget gameWidget, GamePlayer player)
    {
        this.gameWidget = gameWidget;
        this.player = player;

        toolTip = createToolTip();

        rootPanel = createRootPanel();

        rootPanel.addDomHandler(this, MouseOverEvent.getType());
        rootPanel.addDomHandler(this, MouseOutEvent.getType());
    }

    protected ToolTip createToolTip()
    {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.google.gwt.user.client.ui.IsWidget#asWidget()
     */
    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.gwt.client.client.game.IStockItemWidget#createRootPanel()
     */
    @Override
    public ComplexPanel createRootPanel()
    {
        return new VerticalPanel();
    }

    @Override
    public void onMouseOver(MouseOverEvent event)
    {
        gameWidget.getToolTipManager().showToolTip(toolTip);
    }

    @Override
    public void onMouseOut(MouseOutEvent event)
    {
        gameWidget.getToolTipManager().hideToolTip(toolTip);
    }
}
