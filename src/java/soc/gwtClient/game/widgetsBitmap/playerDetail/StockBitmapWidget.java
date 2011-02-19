package soc.gwtClient.game.widgetsBitmap.playerDetail;

import soc.common.board.pieces.City;
import soc.common.board.pieces.Road;
import soc.common.board.pieces.Town;
import soc.common.board.pieces.abstractPieces.PlayerPiece;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.widgetsAbstract.AbstractStockWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.game.widgetsInterface.playerDetail.StockItemWidget;

import com.google.gwt.user.client.ui.Widget;

public class StockBitmapWidget extends AbstractStockWidget
{
    public StockBitmapWidget(GameWidget gamePanel, GamePlayer player)
    {
        super(gamePanel, player);
    }

    @Override
    public StockItemWidget createStockItemWidget(PlayerPiece playerPiece)
    {
        if (playerPiece instanceof Road)
            return new StockRoadBitmapWidget(gamePanel, player);

        if (playerPiece instanceof City)
            return new StockCityBitmapWidget(gamePanel, player);

        if (playerPiece instanceof Town)
            return new StockTownBitmapWidget(gamePanel, player);

        throw new RuntimeException();
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }
}
