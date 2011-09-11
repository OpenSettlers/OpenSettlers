package org.soc.gwt.client.game.widgetsBitmap.playerInfo;

import org.soc.common.game.DevelopmentCardsChangedEvent;
import org.soc.common.game.GamePlayer;
import org.soc.gwt.client.game.widgetsAbstract.playerInfo.AbstractDevelopmentCardsAmountWidget;
import org.soc.gwt.client.images.R;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class DevelopmentCardsAmountBitmapWidget extends
        AbstractDevelopmentCardsAmountWidget
{
  Image devcardImage = new Image(R.icons().developmentCardBack16());
  Label amountDevcards = new Label();

  public DevelopmentCardsAmountBitmapWidget(GamePlayer player)
  {
    super(player);
    devcardImage.setSize("16px", "16px");
    amountDevcards.setText(Integer.toString(player.developmentCards()
            .size()));
    rootPanel.add(devcardImage);
    rootPanel.add(amountDevcards);
  }
  /* (non-Javadoc)
   * 
   * @see org.soc.gwt.client.game.abstractWidgets.AbstractDevelopmentCardsAmountWidget
   * #onDevelopmentCardsChanged (org.soc.common.game.developmentCards.DevelopmentCardsChangedEvent) */
  public void onDevelopmentCardsChanged(DevelopmentCardsChangedEvent event)
  {
    amountDevcards.setText(Integer.toString(player.developmentCards()
            .size()));
  }
}
