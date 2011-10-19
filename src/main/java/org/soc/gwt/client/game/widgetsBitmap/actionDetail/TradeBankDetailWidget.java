package org.soc.gwt.client.game.widgetsBitmap.actionDetail;

import org.soc.common.core.GenericList.Models;
import org.soc.common.game.actions.*;
import org.soc.common.game.trading.*;
import org.soc.common.views.widgetsInterface.main.*;
import org.soc.gwt.client.game.widgetsAbstract.actionDetail.*;

import com.google.gwt.user.client.ui.*;

public class TradeBankDetailWidget extends AbstractActionDetailWidget
{
  private TradeBank tradeBank;

  public TradeBankDetailWidget(GameWidget gameWidget, TradeBank tradeBank)
  {
    super(gameWidget, tradeBank.player());
    this.tradeBank = tradeBank;
    rootPanel.add(new Image(Models.mediumIcon(tradeBank)));
  }
  @Override public GameAction getGameAction()
  {
    return tradeBank;
  }
}
