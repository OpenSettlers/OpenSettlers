package soc.gwtClient.client.game.standard.bitmap;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import soc.common.board.pieces.City;
import soc.common.game.Player;
import soc.gwtClient.client.game.AbstractStockItemWidget;

public class BitmapStockCityWidget extends AbstractStockItemWidget
{
    Image cityImage = new Image("icons/48/City48.png");
    Label amountCities = new Label();
    
    public BitmapStockCityWidget(Player player)
    {
        super(player);
        
        cityImage.setSize("24px", "24px");
        amountCities.setText(Integer.toString(player.getStock().ofType(new City()).size()));
        
        rootPanel.add(cityImage);
        rootPanel.add(amountCities);
    }

}
