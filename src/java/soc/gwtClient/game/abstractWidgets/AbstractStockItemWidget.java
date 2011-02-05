package soc.gwtClient.game.abstractWidgets;

import soc.common.game.player.GamePlayer;

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
    protected GamePanel gamePanel;
    protected PlayerDetailWidget detailWidget;

    public AbstractStockItemWidget(GamePanel gamePanel, GamePlayer player)
    {
        this.gamePanel = gamePanel;
        this.player = player;

        detailWidget = createDetailWidget();

        rootPanel = createRootPanel();

        rootPanel.addDomHandler(this, MouseOverEvent.getType());
        rootPanel.addDomHandler(this, MouseOutEvent.getType());
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
        gamePanel.getDetailContainerManager().showMouseOverDetail(player,
                detailWidget);
    }

    @Override
    public void onMouseOut(MouseOutEvent event)
    {
        gamePanel.getDetailContainerManager().hideMouseOverDetail(player);
    }
}
