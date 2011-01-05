package soc.gwtClient.game.widgets.standard.bitmap;

import soc.common.game.developmentCards.DevelopmentCardsChangedEvent;
import soc.common.game.developmentCards.standard.Soldier;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.abstractWidgets.AbstractLargestArmyWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class LargestArmyBitmapWidget extends AbstractLargestArmyWidget
{
    Image largestAmryImage = new Image(Resources.icons().robberSmall());
    Label amountSoldiers = new Label();
    Soldier soldier = new Soldier();

    public LargestArmyBitmapWidget(GamePlayer player)
    {
        super(player);

        amountSoldiers.setText(Integer.toString(player
                .getPlayedDevelopmentCards().ofType(new Soldier()).size()));
        largestAmryImage.setSize("16px", "16px");

        rootPanel.add(largestAmryImage);
        rootPanel.add(amountSoldiers);
    }

    @Override
    public void onDevelopmentCardsChanged(DevelopmentCardsChangedEvent event)
    {
        amountSoldiers.setText(Integer.toString(player
                .getPlayedDevelopmentCards().ofType(soldier).size()));
    }

}
