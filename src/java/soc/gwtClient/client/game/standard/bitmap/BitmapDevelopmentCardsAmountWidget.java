package soc.gwtClient.client.game.standard.bitmap;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import soc.common.game.Player;
import soc.gwtClient.client.game.AbstractDevelopmentCardsAmountWidget;

public class BitmapDevelopmentCardsAmountWidget extends
        AbstractDevelopmentCardsAmountWidget
{
    Image devcardImage = new Image("icons/48/BlankCard48.png");
    Label amountDevcards = new Label();
    
    public BitmapDevelopmentCardsAmountWidget(Player player)
    {
        super(player);
        
        devcardImage.setSize("24px", "24px");
        amountDevcards.setText(Integer.toString(player.getDevelopmentCardsCount()));
        
        rootPanel.add(devcardImage);
        rootPanel.add(amountDevcards);
    }

}
