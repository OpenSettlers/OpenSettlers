package soc.gwt.client.game.widgetsAbstract.playerInfo;

import soc.common.board.pieces.abstractPieces.StockPiece;
import soc.common.game.player.GamePlayer;
import soc.common.views.widgetsInterface.main.GameWidget;
import soc.common.views.widgetsInterface.payerInfo.StockItemWidgetFactory;
import soc.common.views.widgetsInterface.payerInfo.StockWidget;

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
     * @see soc.gwt.client.client.game.IStockWidget#createRootPanel()
     */
    @Override
    public ComplexPanel createRootPanel()
    {
        return new HorizontalPanel();
    }
}
