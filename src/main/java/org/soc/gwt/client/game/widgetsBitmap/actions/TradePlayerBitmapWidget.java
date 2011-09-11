package org.soc.gwt.client.game.widgetsBitmap.actions;

import org.soc.common.game.GamePhaseChangedEvent;
import org.soc.common.game.GamePhaseChangedEvent.GamePhaseChangedHandler;
import org.soc.common.game.GamePlayer;
import org.soc.common.game.ResourcesChangedEvent;
import org.soc.common.game.ResourcesChangedEvent.ResourcesChangedHandler;
import org.soc.common.game.TurnChangedEvent;
import org.soc.common.game.TurnChangedEvent.TurnChangedHandler;
import org.soc.common.game.TurnPhaseChangedEvent;
import org.soc.common.game.TurnPhaseChangedEvent.TurnPhaseChangedHandler;
import org.soc.common.game.trading.TradePlayer;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.actions.AbstractActionWidget;
import org.soc.gwt.client.images.R;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;

public class TradePlayerBitmapWidget extends AbstractActionWidget implements
        GamePhaseChangedHandler, ResourcesChangedHandler,
        ClickHandler, TurnChangedHandler, TurnPhaseChangedHandler
{
  PushButton btnTradePlayer = new PushButton(new Image(R.icons()
          .tradePlayer48()));
  TradePlayer tradePlayer = new TradePlayer();

  public TradePlayerBitmapWidget(GameWidget gameWidget, GamePlayer player)
  {
    super(gameWidget, player);
    tradePlayer.setPlayer(player);
    player.resources().addResourcesChangedHandler(this);
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
  @Override public void onResourcesChanged(ResourcesChangedEvent resourcesChanged)
  {
    checkEnabled();
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
