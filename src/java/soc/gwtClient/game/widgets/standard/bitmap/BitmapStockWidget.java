package soc.gwtClient.game.widgets.standard.bitmap;

import com.google.gwt.user.client.ui.Widget;

import soc.common.board.pieces.City;
import soc.common.board.pieces.PlayerPiece;
import soc.common.board.pieces.Road;
import soc.common.board.pieces.Town;
import soc.common.game.Game;
import soc.common.game.Player;
import soc.gwtClient.game.abstractWidgets.AbstractStockWidget;
import soc.gwtClient.game.abstractWidgets.IStockItemWidget;

public class BitmapStockWidget extends AbstractStockWidget
{
    public BitmapStockWidget(Game game, Player player)
    {
        super(game, player);
    }

    @Override
    public IStockItemWidget createStockItemWidget(PlayerPiece playerPiece)
    {
        if (playerPiece instanceof Road)
        {
            return new BitmapStockRoadWidget(player);
        }
        if (playerPiece instanceof City)
        {
            return new BitmapStockCityWidget(player);
        }
        if (playerPiece instanceof Town)
        {
            return new BitmapStockTownWidget(player);
        }
        
        throw new RuntimeException();
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }
}
