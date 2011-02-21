package soc.gwtClient.game.widgetsAbstract;

import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.widgetsInterface.generic.ToolTip;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.game.widgetsInterface.playerDetail.StockItemWidget;

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
    protected GameWidget gamePanel;
    protected ToolTip toolTip;

    public AbstractStockItemWidget(GameWidget gamePanel, GamePlayer player)
    {
        this.gamePanel = gamePanel;
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
     * @see soc.gwtClient.client.game.IStockItemWidget#createRootPanel()
     */
    @Override
    public ComplexPanel createRootPanel()
    {
        return new VerticalPanel();
    }

    @Override
    public void onMouseOver(MouseOverEvent event)
    {
        gamePanel.getToolTipManager().showToolTip(toolTip);
    }

    @Override
    public void onMouseOut(MouseOutEvent event)
    {
        gamePanel.getToolTipManager().hideToolTip(toolTip);
    }
}
