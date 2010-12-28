package soc.gwtClient.game.widgets.standard.bitmap.player;

import soc.common.game.Player;
import soc.common.game.developmentCards.DevelopmentCardsChangedEvent;
import soc.gwtClient.game.abstractWidgets.AbstractDevelopmentCardsAmountWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class DevelopmentCardsAmountBitmapWidget extends
        AbstractDevelopmentCardsAmountWidget
{
    Image devcardImage = new Image(Resources.icons().developmentCardBackSmall());
    Label amountDevcards = new Label();

    public DevelopmentCardsAmountBitmapWidget(Player player)
    {
        super(player);

        devcardImage.setSize("16px", "16px");
        amountDevcards.setText(Integer.toString(player
                .getDevelopmentCardsCount()));

        rootPanel.add(devcardImage);
        rootPanel.add(amountDevcards);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.gwtClient.game.abstractWidgets.AbstractDevelopmentCardsAmountWidget
     * #onDevelopmentCardsChanged
     * (soc.common.game.developmentCards.DevelopmentCardsChangedEvent)
     */
    public void onDevelopmentCardsChanged(DevelopmentCardsChangedEvent event)
    {
        amountDevcards.setText(Integer.toString(player
                .getDevelopmentCardsCount()));
    }
}
