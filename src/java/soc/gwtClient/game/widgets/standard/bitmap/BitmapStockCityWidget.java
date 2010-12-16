package soc.gwtClient.game.widgets.standard.bitmap;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import soc.common.board.pieces.City;
import soc.common.board.pieces.PiecesChangedEvent;
import soc.common.board.pieces.PiecesChangedEventHandler;
import soc.common.board.pieces.PlayerPiece;
import soc.common.game.Player;
import soc.gwtClient.game.abstractWidgets.AbstractStockItemWidget;

public class BitmapStockCityWidget extends AbstractStockItemWidget implements PiecesChangedEventHandler
{
    Image cityImage = new Image("icons/48/City48.png");
    Label amountCities = new Label();
    City city = new City();
    
    public BitmapStockCityWidget(Player player)
    {
        super(player);
        
        cityImage.setSize("24px", "24px");
        amountCities.setText(Integer.toString(player.getStock().ofType(City.class).size()));
        
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
            amountCities.setText(Integer.toString(player.getStock().ofType(City.class).size()));            
        }
    }

}
