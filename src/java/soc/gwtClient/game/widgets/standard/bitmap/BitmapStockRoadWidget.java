package soc.gwtClient.game.widgets.standard.bitmap;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import soc.common.board.pieces.Road;
import soc.common.game.Player;
import soc.gwtClient.game.abstractWidgets.AbstractStockItemWidget;

public class BitmapStockRoadWidget extends AbstractStockItemWidget
{
    private Image roadImage = new Image("icons/48/Road48.png");
    private Label roadAmount = new Label();
    
    public BitmapStockRoadWidget(Player player)
    {
        super(player);
        
        roadImage.setSize("24px", "24px");
        roadAmount.setText(Integer.toString(player.getStock().ofType(new Road()).size()));
        
        rootPanel.add(roadImage);
        rootPanel.add(roadAmount);
    }
}
