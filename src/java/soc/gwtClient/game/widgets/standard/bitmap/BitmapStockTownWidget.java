package soc.gwtClient.game.widgets.standard.bitmap;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import soc.common.board.pieces.Town;
import soc.common.game.Player;
import soc.gwtClient.game.abstractWidgets.AbstractStockItemWidget;

public class BitmapStockTownWidget extends AbstractStockItemWidget
{
    private Image townImage = new Image("icons/48/Town48.png");
    private Label townAmount = new Label();
    
    public BitmapStockTownWidget(Player player)
    {
        super(player);
        
        townImage.setSize("24px", "24px");
        townAmount.setText(Integer.toString(player.getStock().ofType(new Town()).size()));
        
        rootPanel.add(townImage);
        rootPanel.add(townAmount);
    }

}
