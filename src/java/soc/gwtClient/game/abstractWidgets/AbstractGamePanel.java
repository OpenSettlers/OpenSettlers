package soc.gwtClient.game.abstractWidgets;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.game.Game;
import soc.common.game.player.GamePlayer;
import soc.common.server.GameServer;
import soc.common.server.GameServerCallback;
import soc.gwtClient.game.CenterWidget;
import soc.gwtClient.game.Point2D;
import soc.gwtClient.game.abstractWidgets.factories.GameWidgetFactory;
import soc.gwtClient.game.behaviour.GameBehaviour;
import soc.gwtClient.game.behaviour.factories.GameBehaviourFactory;
import soc.gwtClient.game.behaviour.factories.NextActionGameBehaviourFactory;
import soc.gwtClient.game.behaviour.factories.ReceiveGameBehaviourFactory;
import soc.gwtClient.game.behaviour.factories.ReceivedActionGameBehaviourFactory;
import soc.gwtClient.game.behaviour.factories.SendGameBehaviourFactory;
import soc.gwtClient.game.behaviour.received.ReceiveGameBehaviour;
import soc.gwtClient.game.widgets.ChatPanel;
import soc.gwtClient.game.widgets.DebugPanel;
import soc.gwtClient.game.widgets.DetailContainerManager;
import soc.gwtClient.game.widgets.GameQueuePanel;
import soc.gwtClient.game.widgets.abstractWidgets.DebugWidget;
import soc.gwtClient.game.widgets.abstractWidgets.LooseCardsDialog;
import soc.gwtClient.game.widgets.abstractWidgets.PlayerStuffWidget;
import soc.gwtClient.game.widgets.abstractWidgets.ResourcesGainedWidget;
import soc.gwtClient.game.widgets.abstractWidgets.StealCardWidget;
import soc.gwtClient.visuals.abstractVisuals.GameBoardVisual;

public abstract class AbstractGamePanel implements GamePanel, CenterWidget,
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
    protected GameWidgetFactory gameWidgetFactory;

    // Left-bottom tab panel
    protected GameHistoryWidget historyWidget;
    protected ChatPanel chatPanel;
    protected GameQueuePanel gameQueuePanel;
    protected DebugWidget debugPanel;

    // Generic info boxes
    protected PlayersWidget playersWidget;
    protected StatusPanel statusPanel;
    protected GameBoardVisual gameBoardVisual;
    protected BankStockPanel bankStockPanel;

    // In-game panels to provide turns funtionality
    protected ResourcesGainedWidget resourcesGainedWidget;
    protected StealCardWidget stealCardWidget;
    protected BankTradeUI bankTradeUI;
    protected LooseCardsDialog looseCardsDialog;
    protected GameOverDialog gameOverDialog;

    protected PlayerStuffWidget playerStuff;
    protected DetailContainerManager detailContainerManager;

    public AbstractGamePanel()
    {
    }

    protected void initialize()
    {
        player = game.getPlayers().get(0);

        receiveBehaviourFactory = new ReceivedActionGameBehaviourFactory(this);
        sendBehaviourFactory = new SendGameBehaviourFactory(this);
        nextActionBehaviourFactory = new NextActionGameBehaviourFactory(this);
        gameWidgetFactory = createGameWidgetFactory();

        bankStockPanel = gameWidgetFactory.createBankStockPanel();
        playersWidget = gameWidgetFactory.createPlayersWidget();
        gameBoardVisual = gameWidgetFactory.createGameBoard(800, 500);
        statusPanel = gameWidgetFactory.createStatusDicePanel();
        historyWidget = gameWidgetFactory.createHistoryWidget();
        bankTradeUI = gameWidgetFactory.createBankTradeUI();
        stealCardWidget = gameWidgetFactory.createStealCardWidget(player);
        resourcesGainedWidget = gameWidgetFactory.createResourcesGainedWidget();
        looseCardsDialog = gameWidgetFactory.createLooseCardsDialog();

        chatPanel = new ChatPanel(this);
        gameQueuePanel = new GameQueuePanel(this);
        debugPanel = new DebugPanel(this);
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
        return stealCardWidget;
    }

    @Override
    public BankTradeUI getBankTradeUI()
    {
        return bankTradeUI;
    }

    @Override
    public GameBoardVisual getGameBoardVisual()
    {
        return gameBoardVisual;
    }

    @Override
    public PlayersWidget getPlayersWidget()
    {
        return playersWidget;
    }

    @Override
    public GameOverDialog getGameOverDialog()
    {
        return gameOverDialog;
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
        bankTradeUI.setPopupPosition(location.getX(), location.getY());
        bankTradeUI.show();
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
        ReceiveGameBehaviour newBehaviour = receiveBehaviourFactory
                .createBehaviour(gameAction);
        if (newBehaviour != null)
        {
            setNewGameBehaviour(newBehaviour);

            if (!newBehaviour.endsManually())
            {
                setBehaviourForNextAction();
            }
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
        GameAction next = game.getActionsQueue().peek();

        // Make sure the next action is meant to be played with the player
        // playing in this GamePanel
        if (next != null && next instanceof TurnAction
                && next.getPlayer().equals(player))
        {
            // Create new behaviour and set it when available
            GameBehaviour newBehaviour = nextActionBehaviourFactory
                    .createBehaviour(next);
            if (newBehaviour != null)
            {
                setNewGameBehaviour(newBehaviour);
            }
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
