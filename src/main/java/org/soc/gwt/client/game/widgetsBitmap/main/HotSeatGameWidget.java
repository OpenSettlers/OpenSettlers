package org.soc.gwt.client.game.widgetsBitmap.main;

import org.soc.common.game.Game;
import org.soc.common.game.TurnChangedEvent;
import org.soc.common.game.TurnChangedEvent.TurnChangedHandler;
import org.soc.common.game.actions.ActionOnGameBoard.DisabledMap;
import org.soc.common.game.actions.GameAction;
import org.soc.common.game.actions.HostStartsGame;
import org.soc.common.server.GameServer.HotSeatServer;
import org.soc.common.views.widgetsInterface.actions.ActionsWidget;
import org.soc.common.views.widgetsInterface.actions.HotSeatActionsPlayersWidget;
import org.soc.common.views.widgetsInterface.dialogs.TradePlayerDialog;
import org.soc.common.views.widgetsInterface.main.CenterWidget;
import org.soc.common.views.widgetsInterface.main.HotSeatTradePlayersWidget;
import org.soc.common.views.widgetsInterface.main.PlayerStuffWidget;
import org.soc.gwt.client.game.widgetsAbstract.main.AbstractGameWidget;

import com.google.gwt.user.client.ui.Panel;

public class HotSeatGameWidget extends AbstractGameWidget implements
        CenterWidget, TurnChangedHandler
{
  private HotSeatActionsPlayersWidget playersActionsWidget;
  private HotSeatTradePlayersWidget hotseatTradePlayersWidget;

  public HotSeatGameWidget() {
    super();
    server = new HotSeatServer(this);
  }
  public void startGame(Game game) {
    HostStartsGame start = new HostStartsGame();
    start.setGame(game);
    start.setPlayer(game.players().get(0));
    server.sendAction(start);
  }
  @Override protected void initialize() {
    super.initialize();
    GameWidgetFactory widgetFactory = gameWidgetLayoutWidget.getGameWidgetFactory();
    looseCardsDialog = widgetFactory.createHotseatLooseCardsDialog();
    playersActionsWidget = widgetFactory.createHotSeatActionsPlayersWidget();
    hotseatTradePlayersWidget = widgetFactory.createHotSeatTradePlayersWidget();
    gameWidgetLayoutWidget.initialize();
    game.addTurnChangedHandler(this);
    boardVisualWidget.boardVisual().setBehaviour(new DisabledMap());
    boardVisualWidget.boardVisual().hideTerritories();
  }
  @Override public Panel getRootWidget() {
    return gameWidgetLayoutWidget.getRootPanel();
  }
  @Override public void receive(GameAction gameAction) {
    // Only initialize the rest of the ui when host started the game
    if (gameAction instanceof HostStartsGame) {
      HostStartsGame hostStartsGame = (HostStartsGame) gameAction;
      game = hostStartsGame.getGame();
      player = game.players().get(0);
      initialize();
    }
    super.receive(gameAction);
  }
  @Override public void onTurnChanged(TurnChangedEvent event) {
    // Hotseat, so when the turn changes, the current player is set to the
    // new player on turn
    if (event.getNewTurn().player().bot() == null)
      player = event.getNewTurn().player();
    if (player.bot() == null)
      playersActionsWidget.setPlayer(player);
  }
  @Override public GameWidgetFactory widgetFactory() {
    return new GameBitmapWidgetFactory(this);
  }
  /** Returns the ActionWidget of the player currently on turn */
  @Override public ActionsWidget actionsWidget() {
    return playersActionsWidget.getCurrentActionsWidget();
  }
  @Override public TradePlayerDialog getTradePlayerUI() {
    return hotseatTradePlayersWidget.getPlayerTradeWidget(player);
  }
  @Override public PlayerStuffWidget getPlayerStuffWidget() {
    // return playersActionsWidget;
    return null;
  }
}
