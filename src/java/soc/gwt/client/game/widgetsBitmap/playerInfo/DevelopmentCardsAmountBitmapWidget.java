package soc.gwt.client.game.widgetsBitmap.playerInfo;

import soc.common.game.developmentCards.DevelopmentCardsChangedEvent;
import soc.common.game.player.GamePlayer;
import soc.gwt.client.game.widgetsAbstract.playerInfo.AbstractDevelopmentCardsAmountWidget;
import soc.gwt.client.images.Resources;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class DevelopmentCardsAmountBitmapWidget extends
                AbstractDevelopmentCardsAmountWidget
{
    Image devcardImage = new Image(Resources.icons().developmentCardBack16());
    Label amountDevcards = new Label();

    public DevelopmentCardsAmountBitmapWidget(GamePlayer player)
    {
        super(player);

        devcardImage.setSize("16px", "16px");
        amountDevcards.setText(Integer.toString(player.getDevelopmentCards()
                        .size()));

        rootPanel.add(devcardImage);
        rootPanel.add(amountDevcards);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.gwt.client.game.abstractWidgets.AbstractDevelopmentCardsAmountWidget
     * #onDevelopmentCardsChanged
     * (soc.common.game.developmentCards.DevelopmentCardsChangedEvent)
     */
    public void onDevelopmentCardsChanged(DevelopmentCardsChangedEvent event)
    {
        amountDevcards.setText(Integer.toString(player.getDevelopmentCards()
                        .size()));
    }
}
