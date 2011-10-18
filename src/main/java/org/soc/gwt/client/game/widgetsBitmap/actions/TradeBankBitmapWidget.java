package org.soc.gwt.client.game.widgetsBitmap.actions;

import org.soc.common.core.GenericList.*;
import org.soc.common.core.GenericList.Adds.*;
import org.soc.common.core.GenericList.AddsList.*;
import org.soc.common.core.GenericList.Removes.*;
import org.soc.common.core.GenericList.RemovesList.*;
import org.soc.common.game.*;
import org.soc.common.game.GamePhaseChangedEvent.GamePhaseChangedHandler;
import org.soc.common.game.TurnPhaseChangedEvent.TurnPhaseChangedHandler;
import org.soc.common.game.trading.*;
import org.soc.common.views.widgetsInterface.main.*;
import org.soc.gwt.client.game.widgetsAbstract.actions.*;
import org.soc.gwt.client.images.*;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*;

public class TradeBankBitmapWidget extends AbstractActionPresenter implements
        GamePhaseChangedHandler, ClickHandler,
        TurnPhaseChangedHandler
{
  PushButton btnTradeBank;
  boolean isTradeBankShown = false;
  TradeBank tradeBank = new TradeBank();

  public TradeBankBitmapWidget(GameWidget gameWidget, GamePlayer player) {
    super(gameWidget, player);
    btnTradeBank = new PushButton(new Image(R.icons().bankTrade48()));
    player.resources().addListAddedHandler(new ListAdded<Resource>() {
      @Override public void listAdded(ImmutableList<Resource> items) {
        checkEnabled();
      }
    });
    player.resources().addListRemovedHandler(new ListRemoved<Resource>() {
      @Override public void listRemoved(ImmutableList<Resource> items) {
        checkEnabled();
      }
    });
    player.ports().addAddedHandler(new Added<Port>() {
      @Override public void added(Port port) {
        checkEnabled();
      }
    });
    player.ports().addRemovedHandler(new Removed<Port>() {
      @Override public void removed(Port port) {
        checkEnabled();
      }
    });
    gameWidget.game().addGamePhaseChangedHandler(this);
    gameWidget.game().addTurnPhaseChangedHandler(this);
    btnTradeBank.addClickHandler(this);
  }
  @Override public Widget asWidget() {
    return btnTradeBank;
  }
  @Override protected void updateEnabled() {
    checkEnabled();
  }
  private void enableUI() {
    btnTradeBank.setEnabled(true);
  }
  private void disableUI() {
    btnTradeBank.setEnabled(false);
  }
  @Override public void onGamePhaseChanged(GamePhaseChangedEvent event) {
    checkEnabled();
  }
  @Override public void onTurnPhaseChanged(TurnPhaseChangedEvent event) {
    checkEnabled();
  }
  private void checkEnabled() {
    if (enabled && player.isOnTurn()) {
      if (gameWidget.game().isAllowed(tradeBank)
              && player.ports().amountGold(player.resources()) > 0) {
        enableUI();
        return;
      }
    }
    disableUI();
  }
  @Override public void onClick(ClickEvent arg0) {
    gameWidget.bankTradeDialog().setPieceToTradeFor(null, null);
  }
}
