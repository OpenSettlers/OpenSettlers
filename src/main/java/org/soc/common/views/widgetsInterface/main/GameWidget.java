package org.soc.common.views.widgetsInterface.main;

import org.soc.common.game.Game;
import org.soc.common.game.GamePlayer;
import org.soc.common.game.actions.Action.ActionPresenter.ActionWidgetFactory;
import org.soc.common.game.actions.GameAction;
import org.soc.common.views.widgetsInterface.actions.ActionDetailWidget.ActionDetailWidgetFactory;
import org.soc.common.views.widgetsInterface.actions.ActionsWidget;
import org.soc.common.views.widgetsInterface.actions.DiceWidget.DiceWidgetFactory;
import org.soc.common.views.widgetsInterface.actions.HotSeatActionsPlayersWidget;
import org.soc.common.views.widgetsInterface.dialogs.BankTradeWidget;
import org.soc.common.views.widgetsInterface.dialogs.GameOverDialog;
import org.soc.common.views.widgetsInterface.dialogs.HotseatLooseCardsDialog;
import org.soc.common.views.widgetsInterface.dialogs.LooseCardsDialog;
import org.soc.common.views.widgetsInterface.dialogs.StealCardWidget;
import org.soc.common.views.widgetsInterface.dialogs.TradePlayerDialog;
import org.soc.common.views.widgetsInterface.generic.Point2D;
import org.soc.common.views.widgetsInterface.generic.ToolTipManager;
import org.soc.common.views.widgetsInterface.main.GamePhaseWidget.GamePhaseStatusWidgetFactory;
import org.soc.common.views.widgetsInterface.playerInfo.PlayersInfoWidget;
import org.soc.common.views.widgetsInterface.playerInfo.StockItemWidget.StockItemWidgetFactory;

public interface GameWidget {
  public Game game();
  // Returns the player currrently playing. In hotseat, this is always the
  // player currently on turn.
  public GamePlayer playingPlayer();
  public GameWidgetFactory widgetFactory();
  // Called by ActionWidget to execute a TurnAction
  public void startAction(GameAction chat);
  public void doAction(GameAction gameAction);
  public void blockUI();
  public void unBlockUI();
  public void showTradeBankPanel();
  public void doneReceiving();
  public ClientFactory clientFactory();
  //
  public StealCardWidget stealCardWidget();
  public HistoryWidget getGameHistoryWidget();
  public PlayersInfoWidget getPlayersInfoWidget();
  public ResourcesGainedWidget resourcesGainedWidget();
  public BoardVisualWidget boardVisualWidget();
  public DetailContainerManager detailWidgets();
  public ActionsWidget actionsWidget();
  public PlayerStuffWidget getPlayerStuffWidget();
  public GameDetailsWidget getDetailsWidget();
  public LooseCardsDialog getLooseCardsDialog();
  public BankTradeWidget bankTradeDialog();
  public TradePlayerDialog getTradePlayerUI();
  public DebugWidget getDebugPanel();
  public GameOverDialog getGameOverDialog();
  public BankStockWidget getbankStockPanel();
  public StatusWidget getStatusWidget();
  public ToolTipManager toolTipManager();
  // Lalala stupid interface methods which need destruction
  public Point2D getTopRightPlayerInfoBoxPosition(GamePlayer player);

  public interface ClientFactory {
    public ActionWidgetFactory getActionWidgetFactory(GamePlayer player);
    public GameWidgetFactory getGameWidgetFactory();
    public ActionDetailWidgetFactory actionDetailWidgetFactory();
    public GamePhaseStatusWidgetFactory getGamePhaseStatusWidgetFactory();
    public StockItemWidgetFactory getStockItemWidgetFactory(GamePlayer player);
    public DiceWidgetFactory getDiceWidgetFactory(GamePlayer player);
  }

  public interface GameWidgetFactory
  {
    public PlayersInfoWidget createPlayersWidget();
    public BankStockWidget createBankStockWidget();
    public BoardVisualWidget createBoardVisualWidget();
    public StatusWidget createStatusDiceWidget();
    public HistoryWidget createHistoryWidget();
    public ChatWidget createChatWidget();
    public DebugWidget createDebugWidget();
    public QueueWidget createQueueWidget();
    public BankTradeWidget createBankTradeWidget();
    public ResourcesGainedWidget createResourcesGainedWidget();
    public PlayerStuffWidget createPlayerStuffWidget(GamePlayer player);
    public StealCardWidget createStealCardWidget(GamePlayer player);
    public LooseCardsDialog createLooseCardsDialog();
    public HotseatLooseCardsDialog createHotseatLooseCardsDialog();
    public GameOverDialog createGameOverDialog();
    public GameDetailsWidget createDetailsWidget();
    public HotSeatActionsPlayersWidget createHotSeatActionsPlayersWidget();
    public HotSeatTradePlayersWidget createHotSeatTradePlayersWidget();
  }
}
