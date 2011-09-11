package org.soc.gwt.client.game.widgetsBitmap.actions;

import org.soc.common.board.PortListChangedEvent;
import org.soc.common.board.PortListChangedEvent.PortListChangedHandler;
import org.soc.common.game.GamePhaseChangedEvent;
import org.soc.common.game.GamePhaseChangedEvent.GamePhaseChangedHandler;
import org.soc.common.game.GamePlayer;
import org.soc.common.game.ResourcesChangedEvent;
import org.soc.common.game.ResourcesChangedEvent.ResourcesChangedHandler;
import org.soc.common.game.TurnPhaseChangedEvent;
import org.soc.common.game.TurnPhaseChangedEvent.TurnPhaseChangedHandler;
import org.soc.common.game.trading.TradeBank;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.actions.AbstractActionWidget;
import org.soc.gwt.client.images.R;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;

public class TradeBankBitmapWidget extends AbstractActionWidget implements
        ResourcesChangedHandler, PortListChangedHandler,
        GamePhaseChangedHandler, ClickHandler,
        TurnPhaseChangedHandler
{
  PushButton btnTradeBank;
  boolean isTradeBankShown = false;
  TradeBank tradeBank = new TradeBank();

  public TradeBankBitmapWidget(GameWidget gameWidget, GamePlayer player)
  {
    super(gameWidget, player);
    btnTradeBank = new PushButton(
            new Image(R.icons().bankTrade48()));
    player.resources().addResourcesChangedHandler(this);
    player.ports().addPortListChangedHandler(this);
    gameWidget.game().addGamePhaseChangedHandler(this);
    gameWidget.game().addTurnPhaseChangedHandler(this);
    btnTradeBank.addClickHandler(this);
  }
  @Override public Widget asWidget()
  {
    return btnTradeBank;
  }
  @Override protected void updateEnabled()
  {
    checkEnabled();
  }
  private void enableUI()
  {
    btnTradeBank.setEnabled(true);
  }
  private void disableUI()
  {
    btnTradeBank.setEnabled(false);
  }
  @Override public void onResourcesChanged(ResourcesChangedEvent resourcesChanged)
  {
    checkEnabled();
  }
  @Override public void onPortListChanged(PortListChangedEvent event)
  {
    checkEnabled();
  }
  @Override public void onGamePhaseChanged(GamePhaseChangedEvent event)
  {
    checkEnabled();
  }
  @Override public void onTurnPhaseChanged(TurnPhaseChangedEvent event)
  {
    checkEnabled();
  }
  private void checkEnabled()
  {
    if (enabled && player.isOnTurn())
    {
      if (gameWidget.game().isAllowed(tradeBank)
              && player.ports().amountGold(
                      player.resources()) > 0)
      {
        enableUI();
        return;
      }
    }
    disableUI();
  }
  @Override public void onClick(ClickEvent arg0)
  {
    gameWidget.bankTradeDialog().setPieceToTradeFor(null, null);
  }
}
