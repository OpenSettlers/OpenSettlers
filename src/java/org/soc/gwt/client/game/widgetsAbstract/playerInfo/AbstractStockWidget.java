package org.soc.gwt.client.game.widgetsAbstract.playerInfo;

import org.soc.common.board.pieces.abstractPieces.StockPiece;
import org.soc.common.game.player.GamePlayer;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.common.views.widgetsInterface.payerInfo.StockItemWidgetFactory;
import org.soc.common.views.widgetsInterface.payerInfo.StockWidget;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;

public abstract class AbstractStockWidget implements StockWidget
{
    protected GameWidget gameWidget;
    protected GamePlayer player;
    protected ComplexPanel rootPanel;

    public AbstractStockWidget(GameWidget gameWidget, GamePlayer player)
    {
        this.gameWidget = gameWidget;
        this.player = player;

        rootPanel = createRootPanel();

        createStockItemWidgetsList();
    }

    private void createStockItemWidgetsList()
    {
        StockItemWidgetFactory factory = gameWidget.getClientFactory()
                .getStockItemWidgetFactory(player);

        for (StockPiece stockPiece : gameWidget.getGame().getRules()
                .getStockPieces())
            rootPanel.add(stockPiece.createStockItemWidget(factory));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.gwt.client.client.game.IStockWidget#createRootPanel()
     */
    @Override
    public ComplexPanel createRootPanel()
    {
        return new HorizontalPanel();
    }
}
