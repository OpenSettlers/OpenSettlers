package soc.gwtClient.game.widgets.standard.bitmap.player;

import soc.common.board.pieces.City;
import soc.common.board.pieces.PiecesChangedEvent;
import soc.common.board.pieces.PiecesChangedEventHandler;
import soc.common.board.pieces.PlayerPiece;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.abstractWidgets.AbstractStockItemWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class StockCityBitmapWidget extends AbstractStockItemWidget implements
        PiecesChangedEventHandler
{
    Image cityImage = new Image(Resources.icons().citySmall());
    Label amountCities = new Label();
    City city = new City();

    public StockCityBitmapWidget(GamePlayer player)
    {
        super(player);

        cityImage.setSize("16px", "16px");
        amountCities.setText(Integer.toString(player.getStock().ofType(
                City.CITY).size()));

        rootPanel.add(cityImage);
        rootPanel.add(amountCities);

        player.getStock().addPiecesChangedEventHandler(this);
    }

    @Override
    public PlayerPiece getStockPiece()
    {
        return city;
    }

    @Override
    public void onPiecesChanged(PiecesChangedEvent event)
    {
        if (event.getChangedPiece() instanceof City)
        {
            amountCities.setText(Integer.toString(player.getStock().ofType(
                    City.CITY).size()));
        }
    }

}
