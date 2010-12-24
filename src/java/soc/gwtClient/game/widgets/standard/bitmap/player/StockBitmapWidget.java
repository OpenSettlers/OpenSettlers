package soc.gwtClient.game.widgets.standard.bitmap.player;

import com.google.gwt.user.client.ui.Widget;

import soc.common.board.pieces.City;
import soc.common.board.pieces.PlayerPiece;
import soc.common.board.pieces.Road;
import soc.common.board.pieces.Town;
import soc.common.game.Game;
import soc.common.game.Player;
import soc.gwtClient.game.abstractWidgets.AbstractStockWidget;
import soc.gwtClient.game.abstractWidgets.IStockItemWidget;

public class StockBitmapWidget extends AbstractStockWidget
{
    public StockBitmapWidget(Game game, Player player)
    {
        super(game, player);
    }

    @Override
    public IStockItemWidget createStockItemWidget(PlayerPiece playerPiece)
    {
        if (playerPiece instanceof Road)
        {
            return new StockRoadBitmapWidget(player);
        }
        if (playerPiece instanceof City)
        {
            return new StockCityBitmapWidget(player);
        }
        if (playerPiece instanceof Town)
        {
            return new StockTownBitmapWidget(player);
        }
        
        throw new RuntimeException();
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }
}
