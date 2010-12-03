package soc.gwtClient.game.widgets.standard.bitmap;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import soc.common.game.Player;
import soc.common.game.developmentCards.Soldier;
import soc.gwtClient.game.abstractWidgets.AbstractLargestArmyWidget;

public class BitmapLargestArmyWidget extends AbstractLargestArmyWidget
{
    Image largestAmryImage = new Image("icons/48/Robber48.png");
    Label amountSoldiers = new Label();
    
    public BitmapLargestArmyWidget(Player player)
    {
        super(player);
        
        amountSoldiers.setText(Integer.toString(player.getPlayedDevelopmentCards().ofType(new Soldier()).size()));
        largestAmryImage.setSize("24px", "24px");
        
        rootPanel.add(largestAmryImage);
        rootPanel.add(amountSoldiers);
    }

}
