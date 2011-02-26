package soc.gwtClient.game.widgetsBitmap.playerInfo;

import soc.common.game.player.GamePlayer;
import soc.common.views.widgetsInterface.main.GameWidget;
import soc.common.views.widgetsInterface.payerInfo.StockItemWidget;
import soc.common.views.widgetsInterface.payerInfo.StockItemWidgetFactory;

public class StockItemBitmapWidgetFactory implements StockItemWidgetFactory
{
    private GameWidget gameWidget;
    private GamePlayer player;

    public StockItemBitmapWidgetFactory(GameWidget gameWidget, GamePlayer player)
    {
        super();
        this.gameWidget = gameWidget;
        this.player = player;
    }

    @Override
    public StockItemWidget createCityStockWidget()
    {
        return new StockCityBitmapWidget(gameWidget, player);
    }

    @Override
    public StockItemWidget createRoadStockWidget()
    {
        return new StockRoadBitmapWidget(gameWidget, player);
    }

    @Override
    public StockItemWidget createTownStockWidget()
    {
        return new StockTownBitmapWidget(gameWidget, player);
    }
}