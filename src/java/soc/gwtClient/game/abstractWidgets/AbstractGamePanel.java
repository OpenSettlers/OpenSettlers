package soc.gwtClient.game.abstractWidgets;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.MessageFromServer;
import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.game.Game;
import soc.common.game.player.GamePlayer;
import soc.common.server.GameServer;
import soc.common.server.GameServerCallback;
import soc.gwtClient.game.CenterWidget;
import soc.gwtClient.game.Point2D;
import soc.gwtClient.game.abstractWidgets.factories.GameWidgetFactory;
import soc.gwtClient.game.behaviour.GameBehaviour;
import soc.gwtClient.game.behaviour.GameBehaviourFactory;
import soc.gwtClient.game.behaviour.StandardGameBoardBehaviourFactory;
import soc.gwtClient.game.dialogs.TradePlayersDialog;
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
    protected GameAction performingAction;

    // Factories
    protected GameBehaviourFactory gameBehaviourFactory;
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
    protected TradePlayersDialog tradePlayers;
    protected BankTradeUI bankTradeUI;
    protected LooseCardsDialog looseCardsDialog;

    protected PlayerStuffWidget playerStuff;
    protected DetailContainerManager detailContainerManager;

    public AbstractGamePanel()
    {
    }

    protected void initialize()
    {
        player = game.getPlayers().get(0);

        gameBehaviourFactory = new StandardGameBoardBehaviourFactory(this);
        gameWidgetFactory = createGameWidgetFactory();

        bankStockPanel = gameWidgetFactory.createBankStockPanel();
        playersWidget = gameWidgetFactory.createPlayersWidget();
        gameBoardVisual = gameWidgetFactory.createGameBoard(800, 500);
        statusPanel = gameWidgetFactory.createStatusDicePanel();
        tradePlayers = new TradePlayersDialog();
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

    public TradePlayersDialog getTradePlayers()
    {
        return tradePlayers;
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
        action.setPlayer(player);
        if (action instanceof TurnAction)
        {
            TurnAction turnAction = (TurnAction) action;
            // Create a behaviour based on our action
            GameBehaviour gameBehaviour = gameBehaviourFactory
                    .createBehaviour(turnAction);

            if (gameBehaviour == null)
            {
                // no behaviour found for the action, send the action right away
                server.sendAction(turnAction);
            }
            else
            {
                // Tell our GameVisual it needs to display the behaviour
                gameBehaviour.start(this);

                // Keep a reference to the action we are currently performing
                performingAction = turnAction;
            }
        }
        else
        {
            // Simply send the action
            server.sendAction(action);
        }
    }

    public void showTradePlayersPanel()
    {
        Point2D location = playersWidget.getTopRightLocation();
        tradePlayers.setPopupPosition(location.getX(), location.getY());
        tradePlayers.show();
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
        if (gameAction instanceof MessageFromServer)
        {
            MessageFromServer messageFromServer = (MessageFromServer) gameAction;
            debugPanel.addError(messageFromServer);
        }
    }

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
}
