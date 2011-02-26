package soc.gwtClient.game.widgetsAbstract.main;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.turns.TurnAction;
import soc.common.game.Game;
import soc.common.game.player.GamePlayer;
import soc.common.server.GameServer;
import soc.common.server.GameServerCallback;
import soc.common.views.behaviour.gameWidget.GameBehaviour;
import soc.common.views.behaviour.gameWidget.factories.GameBehaviourFactory;
import soc.common.views.behaviour.gameWidget.factories.ReceiveGameBehaviourFactory;
import soc.common.views.behaviour.gameWidget.received.ReceiveGameBehaviour;
import soc.common.views.widgetsInterface.actions.ActionsWidget;
import soc.common.views.widgetsInterface.dialogs.GameOverDialog;
import soc.common.views.widgetsInterface.dialogs.LooseCardsDialog;
import soc.common.views.widgetsInterface.dialogs.StealCardWidget;
import soc.common.views.widgetsInterface.dialogs.TradePlayerDialog;
import soc.common.views.widgetsInterface.generic.Point2D;
import soc.common.views.widgetsInterface.generic.ToolTipManager;
import soc.common.views.widgetsInterface.main.BankStockWidget;
import soc.common.views.widgetsInterface.main.BankTradeWidget;
import soc.common.views.widgetsInterface.main.BoardVisualWidget;
import soc.common.views.widgetsInterface.main.ChatWidget;
import soc.common.views.widgetsInterface.main.ClientFactory;
import soc.common.views.widgetsInterface.main.DebugWidget;
import soc.common.views.widgetsInterface.main.GameDetailsWidget;
import soc.common.views.widgetsInterface.main.GamePanelLayoutWidget;
import soc.common.views.widgetsInterface.main.GameWidget;
import soc.common.views.widgetsInterface.main.GameWidgetFactory;
import soc.common.views.widgetsInterface.main.HistoryWidget;
import soc.common.views.widgetsInterface.main.PlayerStuffWidget;
import soc.common.views.widgetsInterface.main.QueueWidget;
import soc.common.views.widgetsInterface.main.ResourcesGainedWidget;
import soc.common.views.widgetsInterface.main.StatusWidget;
import soc.common.views.widgetsInterface.payerInfo.PlayersInfoWidget;
import soc.gwtClient.game.DefaultClientFactory;
import soc.gwtClient.game.DetailContainerManagerImpl;
import soc.gwtClient.game.widgetsBitmap.main.DesktopGamePanelLayout;
import soc.gwtClient.game.widgetsBitmap.main.ToolTipManagerImpl;
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

    // Factory to create various stuff
    protected ClientFactory clientFactory;

    // Left-bottom tab panel
    protected HistoryWidget historyWidget;
    protected ChatWidget chatPanel;
    protected QueueWidget queueWidget;
    protected DebugWidget debugWidget;
    protected GameDetailsWidget gameDetailsWidget;

    protected GamePanelLayoutWidget gameWidgetLayoutWidget;

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
    protected DetailContainerManagerImpl detailContainerManager;
    protected ToolTipManager toolTipManager;

    public AbstractGameWidget()
    {
        clientFactory = new DefaultClientFactory(this);
    }

    protected void initialize()
    {
        player = game.getPlayers().get(0);

        gameWidgetLayoutWidget = new DesktopGamePanelLayout(this);

        GameWidgetFactory gameWidgetFactory = clientFactory
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
        detailContainerManager = new DetailContainerManagerImpl(this);
        toolTipManager = new ToolTipManagerImpl();
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.gwtClient.main.CenterWidget#getRootWidget()
     */
    @Override
    public Widget getRootWidget()
    {
        return gameWidgetLayoutWidget.getRootPanel();
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
    public DetailContainerManagerImpl getDetailContainerManager()
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
    public ToolTipManager getToolTipManager()
    {
        // TODO Auto-generated method stub
        return null;
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

    /**
     * @return the clientFactory
     */
    public ClientFactory getClientFactory()
    {
        return clientFactory;
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
        GameBehaviour gameBehaviour = action.getSendBehaviour(null);

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
     * Receive an action from the game server Check if there's any behaviour
     * associated with receiving,
     * 
     * @see
     * soc.common.server.IGameServerCallback#receive(soc.common.actions.gameAction
     * .GameAction)
     */
    @Override
    public void receive(GameAction gameAction)
    {
        ReceiveGameBehaviour newBehaviour;
        ReceiveGameBehaviourFactory normalReceiveBehaviourFactory = clientFactory
                .getBehaviourFactory().getReceiveBehaviourFactory();
        ReceiveGameBehaviourFactory opponentReceiveBehaviourFactory = clientFactory
                .getBehaviourFactory().getOpponentReceiveBehaviourFactory();

        // Grab new game behaviour for received action
        if (gameAction.getPlayer().equals(player))
            newBehaviour = gameAction
                    .getReceiveBehaviour(normalReceiveBehaviourFactory);
        else
            newBehaviour = gameAction
                    .getOpponentReceiveBehaviour(opponentReceiveBehaviourFactory);

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
            GameBehaviourFactory factory = clientFactory.getBehaviourFactory()
                    .getNextActionBehaviourFactory();
            // Create new behaviour and set it when available
            GameBehaviour newBehaviour = next.getNextActionBehaviour(factory);

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
