package soc.gwt.client.game.widgetsBitmap.playerInfo;

import soc.common.game.player.GamePlayer;
import soc.common.views.widgetsInterface.main.GameWidget;
import soc.gwt.client.game.widgetsAbstract.playerInfo.AbstractStockWidget;

import com.google.gwt.user.client.ui.Widget;

public class StockBitmapWidget extends AbstractStockWidget
{
    public StockBitmapWidget(GameWidget gameWidget, GamePlayer player)
    {
        super(gameWidget, player);
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }
}
