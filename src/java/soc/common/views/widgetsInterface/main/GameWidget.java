package soc.common.views.widgetsInterface.main;

import soc.common.actions.gameAction.GameAction;
import soc.common.game.Game;
import soc.common.game.player.GamePlayer;
import soc.common.views.widgetsInterface.actions.ActionsWidget;
import soc.common.views.widgetsInterface.dialogs.BankTradeWidget;
import soc.common.views.widgetsInterface.dialogs.GameOverDialog;
import soc.common.views.widgetsInterface.dialogs.LooseCardsDialog;
import soc.common.views.widgetsInterface.dialogs.StealCardWidget;
import soc.common.views.widgetsInterface.dialogs.TradePlayerDialog;
import soc.common.views.widgetsInterface.generic.Point2D;
import soc.common.views.widgetsInterface.generic.ToolTipManager;
import soc.common.views.widgetsInterface.payerInfo.PlayersInfoWidget;

public interface GameWidget
{
    // The current game
    public Game getGame();

    // Returns the player currrently playing. In hotseat, this is always the
    // player currently on turn.
    public GamePlayer getPlayingPlayer();

    // Factory for creating all the widgetsn
    public GameWidgetFactory createGameWidgetFactory();

    // Called by ActionWidget to execute a TurnAction
    public void startAction(GameAction chat);

    // Called by behaviours when the prepared action is ripe for sending to the
    // server
    public void sendAction(GameAction gameAction);

    public StealCardWidget getStealCardWidget();

    public HistoryWidget getGameHistoryWidget();

    public PlayersInfoWidget getPlayersInfoWidget();

    public ResourcesGainedWidget getResourcesGainedWidget();

    public BoardVisualWidget getBoardVisualWidget();

    public DetailContainerManager getDetailContainerManager();

    public ActionsWidget getActionsWidget();

    public PlayerStuffWidget getPlayerStuffWidget();

    public GameDetailsWidget getDetailsWidget();

    public LooseCardsDialog getLooseCardsDialog();

    public BankTradeWidget getBankTradeDialog();

    public TradePlayerDialog getTradePlayerUI();

    // Lalala stupid interface methods which need destruction
    public Point2D getTopRightPlayerInfoBoxPosition(GamePlayer player);

    public void showTradeBankPanel();

    public DebugWidget getDebugPanel();

    public void doneReceiveBehaviour();

    public void blockUI();

    public void unBlockUI();

    public GameOverDialog getGameOverDialog();

    public BankStockWidget getbankStockPanel();

    public StatusWidget getStatusWidget();

    public ToolTipManager getToolTipManager();

    public ClientFactory getClientFactory();
}
