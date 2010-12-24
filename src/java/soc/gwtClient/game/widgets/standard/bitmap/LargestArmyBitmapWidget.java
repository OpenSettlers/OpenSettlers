package soc.gwtClient.game.widgets.standard.bitmap;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import soc.common.game.Player;
import soc.common.game.developmentCards.DevelopmentCardsChangedEvent;
import soc.common.game.developmentCards.standard.Soldier;
import soc.gwtClient.game.abstractWidgets.AbstractLargestArmyWidget;

public class LargestArmyBitmapWidget extends AbstractLargestArmyWidget
{
    Image largestAmryImage = new Image("iconz/48/Robber48.png");
    Label amountSoldiers = new Label();
    Soldier soldier = new Soldier();
    
    public LargestArmyBitmapWidget(Player player)
    {
        super(player);
        
        amountSoldiers.setText(Integer.toString(player.getPlayedDevelopmentCards().ofType(new Soldier()).size()));
        largestAmryImage.setSize("24px", "24px");
        
        rootPanel.add(largestAmryImage);
        rootPanel.add(amountSoldiers);
    }

    @Override
    public void onDevelopmentCardsChanged(DevelopmentCardsChangedEvent event)
    {
        amountSoldiers.setText(Integer.toString(player.getPlayedDevelopmentCards().ofType(soldier).size()));
    }

}
