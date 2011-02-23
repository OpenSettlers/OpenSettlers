package soc.gwtClient.game.widgetsBitmap.playerInfo;

import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.game.widgetsInterface.playerInfo.StockItemWidget;
import soc.gwtClient.game.widgetsInterface.playerInfo.StockItemWidgetFactory;

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