package soc.gwtClient.game.widgetsAbstract;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.game.Game;
import soc.common.game.player.GamePlayer;
import soc.common.server.GameServer;
import soc.common.server.GameServerCallback;
import soc.gwtClient.game.DetailContainerManager;
import soc.gwtClient.game.Point2D;
import soc.gwtClient.game.behaviour.gameBoard.factories.GameBehaviourFactory;
import soc.gwtClient.game.behaviour.gameBoard.factories.NextActionGameBehaviourFactory;
import soc.gwtClient.game.behaviour.gameBoard.factories.ReceiveGameBehaviourFactory;
import soc.gwtClient.game.behaviour.gameBoard.factories.ReceivedActionGameBehaviourFactory;
import soc.gwtClient.game.behaviour.gameBoard.factories.SendGameBehaviourFactory;
import soc.gwtClient.game.behaviour.gameBoard.received.ReceiveGameBehaviour;
import soc.gwtClient.game.behaviour.gameWidget.GameBehaviour;
import soc.gwtClient.game.widgetsBitmap.main.DesktopGamePanelLayout;
import soc.gwtClient.game.widgetsBitmap.main.GameDetailsWidget;
import soc.gwtClient.game.widgetsInterface.actions.ActionsWidget;
import soc.gwtClient.game.widgetsInterface.dialogs.GameOverDialog;
import soc.gwtClient.game.widgetsInterface.dialogs.LooseCardsDialog;
import soc.gwtClient.game.widgetsInterface.dialogs.StealCardWidget;
import soc.gwtClient.game.widgetsInterface.dialogs.TradePlayerDialog;
import soc.gwtClient.game.widgetsInterface.main.BankStockWidget;
import soc.gwtClient.game.widgetsInterface.main.BankTradeWidget;
import soc.gwtClient.game.widgetsInterface.main.BoardVisualWidget;
import soc.gwtClient.game.widgetsInterface.main.ChatWidget;
import soc.gwtClient.game.widgetsInterface.main.DebugWidget;
import soc.gwtClient.game.widgetsInterface.main.GamePanelLayoutWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidgetFactory;
import soc.gwtClient.game.widgetsInterface.main.HistoryWidget;
import soc.gwtClient.game.widgetsInterface.main.PlayerStuffWidget;
import soc.gwtClient.game.widgetsInterface.main.QueueWidget;
import soc.gwtClient.game.widgetsInterface.main.ResourcesGainedWidget;
import soc.gwtClient.game.widgetsInterface.main.StatusWidget;
import soc.gwtClient.game.widgetsInterface.playerDetail.PlayersInfoWidget;
import soc.gwtClient.main.CenterWidget;

import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractGameWidget implements GameWidget, CenterWidget,
        GameServerCallback
{
    // Variables for the game panel
    protected GameServer server;
    protected Game game;
    protected GameBehaviour gameBehaviour;
    protected GamePlayer player;

    // Factories
    protected ReceiveGameBehaviourFactory receiveBehaviourFactory;
    protected GameBehaviourFactory sendBehaviourFactory;
    protected GameBehaviourFactory nextActionBehaviourFactory;
    protected ReceiveGameBehaviourFactory opponentBehaviourFactory;

    // Left-bottom tab panel
    protected HistoryWidget historyWidget;
    protected ChatWidget chatPanel;
    protected QueueWidget queueWidget;
    protected DebugWidget debugWidget;
    protected GameDetailsWidget gameDetailsWidget;

    protected GamePanelLayoutWidget gamePanelLayoutWidget;

    // Generic info boxes
    protected PlayersInfoWidget playersWidget;
    protected StatusWidget statusWidget;
    protected BoardVisualWidget boardVisualWidget;
    protected BankStockWidget bankStockPanel;

    // In-game panels to provide turns funtionality
    protected ResourcesGainedWidget resourcesGainedWidget;
    protected StealCardWidget stealCardDialog;
    protected BankTradeWidget bankTradeWidget;
    protected LooseCardsDialog looseCardsDialog;
    protected GameOverDialog gameOverDialog;
    protected TradePlayerDialog tradePlayerDialog;
    protected ActionsWidget actionsWidget;

    protected PlayerStuffWidget playerStuff;
    protected DetailContainerManager detailContainerManager;

    public AbstractGameWidget()
    {
    }

    protected void initialize()
    {
        player = game.getPlayers().get(0);

        gamePanelLayoutWidget = new DesktopGamePanelLayout(this);

        receiveBehaviourFactory = new ReceivedActionGameBehaviourFactory(this);
        sendBehaviourFactory = new SendGameBehaviourFactory(this);
        nextActionBehaviourFactory = new NextActionGameBehaviourFactory(this);
        GameWidgetFactory gameWidgetFactory = gamePanelLayoutWidget
                .getGameWidgetFactory();

        bankStockPanel = gameWidgetFactory.createBankStockWidget();
        playersWidget = gameWidgetFactory.createPlayersWidget();
        boardVisualWidget = gameWidgetFactory.createBoardVisualWidget();
        statusWidget = gameWidgetFactory.createStatusDiceWidget();

        historyWidget = gameWidgetFactory.createHistoryWidget();
        chatPanel = gameWidgetFactory.createChatWidget();
        debugWidget = gameWidgetFactory.createDebugWidget();
        queueWidget = gameWidgetFactory.createQueueWidget();

        bankTradeWidget = gameWidgetFactory.createBankTradeWidget();
        stealCardDialog = gameWidgetFactory.createStealCardWidget(player);
        resourcesGainedWidget = gameWidgetFactory.createResourcesGainedWidget();
        gameDetailsWidget = gameWidgetFactory.createDetailsWidget();
        looseCardsDialog = gameWidgetFactory.createLooseCardsDialog();

        chatPanel = gameWidgetFactory.createChatWidget();
        queueWidget = gameWidgetFactory.createQueueWidget();
        debugWidget = gameWidgetFactory.createDebugWidget();
        detailContainerManager = new DetailContainerManager(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.gwtClient.main.CenterWidget#getRootWidget()
     */
    @Override
    public Widget getRootWidget()
    {
        return gamePanelLayoutWidget.getRootPanel();
    }

    @Override
    public HistoryWidget getGameHistoryWidget()
    {
        return historyWidget;
    }

    @Override
    public BankStockWidget getbankStockPanel()
    {
        return bankStockPanel;
    }

    @Override
    public DetailContainerManager getDetailContainerManager()
    {
        return detailContainerManager;
    }

    @Override
    public ResourcesGainedWidget getResourcesGainedWidget()
    {
        return resourcesGainedWidget;
    }

    @Override
    public StealCardWidget getStealCardWidget()
    {
        return stealCardDialog;
    }

    @Override
    public BankTradeWidget getBankTradeUI()
    {
        return bankTradeWidget;
    }

    @Override
    public BoardVisualWidget getBoardVisualWidget()
    {
        return boardVisualWidget;
    }

    @Override
    public PlayersInfoWidget getPlayersInfoWidget()
    {
        return playersWidget;
    }

    @Override
    public GameOverDialog getGameOverDialog()
    {
        return gameOverDialog;
    }

    public BankStockWidget getBankStockPanel()
    {
        return bankStockPanel;
    }

    @Override
    public LooseCardsDialog getLooseCardsDialog()
    {
        return looseCardsDialog;
    }

    @Override
    public GameDetailsWidget getDetailsWidget()
    {
        return gameDetailsWidget;
    }

    @Override
    public DebugWidget getDebugPanel()
    {
        return debugWidget;
    }

    @Override
    public StatusWidget getStatusWidget()
    {
        return statusWidget;
    }

    @Override
    public Game getGame()
    {
        return game;
    }

    public void sendAction(GameAction gameAction)
    {
        server.sendAction(gameAction);
    }

    @Override
    public GamePlayer getPlayingPlayer()
    {
        return player;
    }

    @Override
    public ActionsWidget getActionsWidget()
    {
        return actionsWidget;
    }

    @Override
    public PlayerStuffWidget getPlayerStuffWidget()
    {
        return playerStuff;
    }

    @Override
    public TradePlayerDialog getTradePlayerUI()
    {
        return tradePlayerDialog;
    }

    @Override
    public void startAction(GameAction action)
    {
        // Check if there is code forgetting to set the player in the spawned
        // GameAction
        if (action.getPlayer() == null)
        {
            action.setPlayer(player);
        }

        // Create a behaviour based on our action
        GameBehaviour gameBehaviour = sendBehaviourFactory
                .createBehaviour((TurnAction) action);

        if (gameBehaviour != null)
        {
            // Tell our GameVisual it should display the behaviour
            gameBehaviour.start(this);
            return;
        }

        // Simply send the action
        sendAction(action);
    }

    public void showTradeBankPanel()
    {
        Point2D location = playersWidget.getTopRightLocation();
        bankTradeWidget.setPopupPosition(location.getX(), location.getY());
        bankTradeWidget.show();
    }

    /*
     * Receive an action from the game server
     * 
     * @see
     * soc.common.server.IGameServerCallback#receive(soc.common.actions.gameAction
     * .GameAction)
     */
    @Override
    public void receive(GameAction gameAction)
    {
        // Grab new game behaviour for received action
        ReceiveGameBehaviour newBehaviour = receiveBehaviourFactory
                .createBehaviour(gameAction);

        if (newBehaviour != null)
        {
            setNewGameBehaviour(newBehaviour);

            if (!newBehaviour.endsManually())
                setBehaviourForNextAction();
        }
        else
        {
            setBehaviourForNextAction();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.gwtClient.game.abstractWidgets.GamePanel#doneReceiveBehaviour()
     */
    @Override
    public void doneReceiveBehaviour()
    {
        setBehaviourForNextAction();
    }

    /*
     * Checks if another GameAction resides on the queue. If so, it looks for
     * associated behaviour and sets it accordingly.
     */
    protected void setBehaviourForNextAction()
    {
        // Grab the next action on the queue
        GameAction next = game.getActionsQueue().getBlockingActions(player);

        // Make sure the next action is meant to be played with the player
        // playing in this GamePanel
        if (next != null && next instanceof TurnAction
                && next.getPlayer().equals(player))
        {
            // Create new behaviour and set it when available
            GameBehaviour newBehaviour = nextActionBehaviourFactory
                    .createBehaviour(next);

            if (newBehaviour != null)
                setNewGameBehaviour(newBehaviour);
        }
    }

    /*
     * Updates the current behaviour to given behaviour
     */
    protected void setNewGameBehaviour(GameBehaviour newGameBehaviour)
    {
        if (gameBehaviour != null)
            gameBehaviour.finish();

        gameBehaviour = newGameBehaviour;
        gameBehaviour.start(this);
    }

    @Override
    public Point2D getTopRightPlayerInfoBoxPosition(GamePlayer player)
    {
        return playersWidget.getTopRightLocation(player);
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.gwtClient.game.abstractWidgets.GamePanel#blockUI()
     */
    @Override
    public void blockUI()
    {
        getActionsWidget().setEnabled(false);
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.gwtClient.game.abstractWidgets.GamePanel#unBlockUI()
     */
    @Override
    public void unBlockUI()
    {
        getActionsWidget().setEnabled(true);
    }

}
