package soc.gwtClient.game.widgetsBitmap.playerInfo;

import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.widgetsAbstract.playerInfo.AbstractStockWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;

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
