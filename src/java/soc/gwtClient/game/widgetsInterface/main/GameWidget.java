package soc.gwtClient.game.widgetsInterface.main;

import soc.common.actions.gameAction.GameAction;
import soc.common.game.Game;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.DetailContainerManager;
import soc.gwtClient.game.Point2D;
import soc.gwtClient.game.widgetsInterface.actions.ActionsWidget;
import soc.gwtClient.game.widgetsInterface.dialogs.GameOverDialog;
import soc.gwtClient.game.widgetsInterface.dialogs.LooseCardsDialog;
import soc.gwtClient.game.widgetsInterface.dialogs.StealCardWidget;
import soc.gwtClient.game.widgetsInterface.dialogs.TradePlayerDialog;
import soc.gwtClient.game.widgetsInterface.playerDetail.PlayersInfoWidget;

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

    public LooseCardsDialog getLooseCardsDialog();

    public BankTradeWidget getBankTradeUI();

    public TradePlayerDialog getTradePlayerUI();

    // Lalala stupid interface methods which need destruction
    public Point2D getTopRightPlayerInfoBoxPosition(GamePlayer player);

    public Point2D getTopLeftDiceWidgetPosition();

    public void showTradeBankPanel();

    public DebugWidget getDebugPanel();

    public void doneReceiveBehaviour();

    public void blockUI();

    public void unBlockUI();

    public GameOverDialog getGameOverDialog();

    public BankStockWidget getbankStockPanel();

    public StatusWidget getStatusWidget();
}
