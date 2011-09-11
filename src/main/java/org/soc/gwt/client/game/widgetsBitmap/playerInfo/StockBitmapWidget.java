package org.soc.gwt.client.game.widgetsBitmap.playerInfo;

import org.soc.common.game.GamePlayer;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.playerInfo.AbstractStockWidget;

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
