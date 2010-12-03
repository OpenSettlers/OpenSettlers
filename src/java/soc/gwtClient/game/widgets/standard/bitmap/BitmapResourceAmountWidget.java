package soc.gwtClient.game.widgets.standard.bitmap;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import soc.common.game.Player;
import soc.gwtClient.game.abstractWidgets.AbstractResourceAmountWidget;

public class BitmapResourceAmountWidget extends AbstractResourceAmountWidget
{
    Image cardImage = new Image("icons/48/BlankCard48.png");
    Label lblAmountResources = new Label();
    
    public BitmapResourceAmountWidget(Player player)
    {
        super(player);
        
        cardImage.setSize("24px", "24px");
        lblAmountResources.setText(Integer.toString(player.getResources().size()));
        
        rootPanel.add(cardImage);
        rootPanel.add(lblAmountResources);
    }

}
