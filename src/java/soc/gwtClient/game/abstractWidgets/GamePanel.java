package soc.gwtClient.game.abstractWidgets;

import soc.common.actions.gameAction.GameAction;
import soc.common.game.Game;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.Point2D;
import soc.gwtClient.game.abstractWidgets.factories.GameWidgetFactory;
import soc.gwtClient.game.widgets.DetailContainerManager;
import soc.gwtClient.game.widgets.abstractWidgets.DebugWidget;
import soc.gwtClient.game.widgets.abstractWidgets.LooseCardsDialog;
import soc.gwtClient.game.widgets.abstractWidgets.ResourcesGainedWidget;
import soc.gwtClient.game.widgets.abstractWidgets.StealCardWidget;
import soc.gwtClient.visuals.abstractVisuals.GameBoardVisual;

public interface GamePanel
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

    public PlayersWidget getPlayersWidget();

    public ResourcesGainedWidget getResourcesGainedWidget();

    public GameBoardVisual getGameBoardVisual();

    public DetailContainerManager getDetailContainerManager();

    public ActionsWidget getActionsWidget();

    public LooseCardsDialog getLooseCardsDialog();

    public BankTradeUI getBankTradeUI();

    // Lalala stupid interface methods which need destruction
    public Point2D getTopRightPlayerInfoBoxPosition(GamePlayer player);

    public Point2D getTopLeftDiceWidgetPosition();

    public void showTradeBankPanel();

    public void showTradePlayersPanel();

    public DebugWidget getDebugPanel();

    public void doneReceiveBehaviour();

    public void blockUI();

    public void unBlockUI();
}
