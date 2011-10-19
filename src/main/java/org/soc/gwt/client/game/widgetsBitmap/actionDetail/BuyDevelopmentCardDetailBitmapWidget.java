package org.soc.gwt.client.game.widgetsBitmap.actionDetail;

import org.soc.common.core.GenericList.Models;
import org.soc.common.game.actions.*;
import org.soc.common.views.widgetsInterface.main.*;
import org.soc.gwt.client.game.widgetsAbstract.actionDetail.*;

import com.google.gwt.user.client.ui.*;

public class BuyDevelopmentCardDetailBitmapWidget extends
        AbstractActionDetailWidget
{
  private BuyDevelopmentCard buyDevelopmentCard;

  public BuyDevelopmentCardDetailBitmapWidget(GameWidget gameWidget,
          BuyDevelopmentCard buyDevelopmentCard)
  {
    super(gameWidget, buyDevelopmentCard.player());
    this.buyDevelopmentCard = buyDevelopmentCard;
    rootPanel.add(new Image(Models.mediumIcon(buyDevelopmentCard)));
  }
  @Override public GameAction getGameAction()
  {
    return buyDevelopmentCard;
  }
}
