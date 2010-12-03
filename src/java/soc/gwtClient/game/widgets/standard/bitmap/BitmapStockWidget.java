package soc.gwtClient.client.game.standard.bitmap;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

import soc.common.board.pieces.City;
import soc.common.board.pieces.PlayerPiece;
import soc.common.board.pieces.Road;
import soc.common.board.pieces.Ship;
import soc.common.board.pieces.Town;
import soc.common.game.Game;
import soc.common.game.Player;
import soc.gwtClient.client.game.AbstractStockWidget;
import soc.gwtClient.client.game.IDevelopmentCardsAmountWidget;
import soc.gwtClient.client.game.ILargestArmyWidget;
import soc.gwtClient.client.game.ILongestRoadWidget;
import soc.gwtClient.client.game.IResourceAmountWidget;
import soc.gwtClient.client.game.IStockItemWidget;
import soc.gwtClient.client.game.IVictoryPointsWidget;

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
