package soc.gwtClient.game.widgetsBitmap.playerInfo;

import soc.common.board.pieces.City;
import soc.common.board.pieces.Road;
import soc.common.board.pieces.Town;
import soc.common.board.pieces.abstractPieces.PlayerPiece;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.widgetsAbstract.playerInfo.AbstractStockWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.game.widgetsInterface.playerInfo.StockItemWidget;

import com.google.gwt.user.client.ui.Widget;

public class StockBitmapWidget extends AbstractStockWidget
{
    public StockBitmapWidget(GameWidget gameWidget, GamePlayer player)
    {
        super(gameWidget, player);
    }

    @Override
    public StockItemWidget createStockItemWidget(PlayerPiece playerPiece)
    {
        if (playerPiece instanceof Road)
            return new StockRoadBitmapWidget(gameWidget, player);

        if (playerPiece instanceof City)
            return new StockCityBitmapWidget(gameWidget, player);

        if (playerPiece instanceof Town)
            return new StockTownBitmapWidget(gameWidget, player);

        throw new RuntimeException();
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }
}
