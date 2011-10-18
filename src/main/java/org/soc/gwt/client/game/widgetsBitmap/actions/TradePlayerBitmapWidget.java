package org.soc.gwt.client.game.widgetsBitmap.actions;

import org.soc.common.core.GenericList.*;
import org.soc.common.core.GenericList.AddsList.*;
import org.soc.common.core.GenericList.RemovesList.*;
import org.soc.common.game.*;
import org.soc.common.game.GamePhaseChangedEvent.GamePhaseChangedHandler;
import org.soc.common.game.TurnChangedEvent.TurnChangedHandler;
import org.soc.common.game.TurnPhaseChangedEvent.TurnPhaseChangedHandler;
import org.soc.common.game.trading.*;
import org.soc.common.views.widgetsInterface.main.*;
import org.soc.gwt.client.game.widgetsAbstract.actions.*;
import org.soc.gwt.client.images.*;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*;

public class TradePlayerBitmapWidget extends AbstractActionPresenter implements
        GamePhaseChangedHandler,
        ClickHandler, TurnChangedHandler, TurnPhaseChangedHandler
{
  PushButton btnTradePlayer = new PushButton(new Image(R.icons()
          .tradePlayer48()));
  TradePlayer tradePlayer = new TradePlayer();

  public TradePlayerBitmapWidget(GameWidget gameWidget, GamePlayer player)
  {
    super(gameWidget, player);
    tradePlayer.setPlayer(player);
    player.resources().addListRemovedHandler(new ListRemoved<Resource>() {
      @Override public void listRemoved(ImmutableList<Resource> items) {
        checkEnabled();
      }
    });
    player.resources().addListAddedHandler(new ListAdded<Resource>() {
      @Override public void listAdded(ImmutableList<Resource> items) {
        checkEnabled();
      }
    });
    gameWidget.game().addGamePhaseChangedHandler(this);
    gameWidget.game().addTurnPhaseChangedHandler(this);
    gameWidget.game().addTurnChangedHandler(this);
    btnTradePlayer.addClickHandler(this);
  }
  @Override public Widget asWidget()
  {
    return btnTradePlayer;
  }
  @Override protected void updateEnabled()
  {
    checkEnabled();
  }
  private void enableUI()
  {
    btnTradePlayer.setEnabled(true);
  }
  private void disableUI()
  {
    btnTradePlayer.setEnabled(false);
  }
  @Override public void onGamePhaseChanged(GamePhaseChangedEvent event)
  {
    checkEnabled();
  }
  @Override public void onTurnChanged(TurnChangedEvent event)
  {
    checkEnabled();
  }
  @Override public void onTurnPhaseChanged(TurnPhaseChangedEvent event)
  {
    checkEnabled();
  }
  private void checkEnabled()
  {
    if (enabled && player.isOnTurn()
            && gameWidget.game().isAllowed(tradePlayer)
            && player.resources().size() > 0)
    {
      enableUI();
      return;
    }
    disableUI();
  }
  @Override public void onClick(ClickEvent arg0)
  {
    gameWidget.getTradePlayerUI().show();
  }
}
