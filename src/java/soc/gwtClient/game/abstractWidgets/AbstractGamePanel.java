package soc.gwtClient.game.abstractWidgets;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.MessageFromServer;
import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.board.pieces.PlayerPiece;
import soc.common.client.behaviour.GameBehaviourFactory;
import soc.common.client.behaviour.StandardGameBehaviourFactory;
import soc.common.client.behaviour.game.DisabledMap;
import soc.common.client.behaviour.game.GameBehaviour;
import soc.common.client.visuals.game.GameBoardVisual;
import soc.common.game.Game;
import soc.common.game.player.GamePlayer;
import soc.common.server.GameServer;
import soc.common.server.IGameServerCallback;
import soc.gwtClient.game.CenterWidget;
import soc.gwtClient.game.Point2D;
import soc.gwtClient.game.dialogs.NewGameDialog;
import soc.gwtClient.game.dialogs.TradePlayersDialog;
import soc.gwtClient.game.widgets.ChatPanel;
import soc.gwtClient.game.widgets.DebugPanel;
import soc.gwtClient.game.widgets.GameQueuePanel;
import soc.gwtClient.game.widgets.abstractWidgets.DebugWidget;

public abstract class AbstractGamePanel implements IGamePanel, CenterWidget,
        IGameServerCallback
{
    protected GameServer server;
    protected Game game;
    protected NewGameDialog newGameWindow;
    protected IActionsWidget buildPallette;
    protected BankStockPanel bankStockPanel;
    protected BankTradeUI bankTradeUI;
    protected GameBehaviourFactory gameBehaviourFactory;
    protected GameBoardVisual gameBoardVisual;
    protected GameAction performingAction;
    protected IPlayersWidget playersWidget;
    protected GamePlayer player;
    protected HandCardsWidget handCards;
    protected StatusPanel statusPanel;
    protected TradePlayersDialog tradePlayers;
    protected GameHistoryWidget historyWidget;
    protected ChatPanel chatPanel;
    protected GameQueuePanel gameQueuePanel;
    protected DebugWidget debugPanel;

    public AbstractGamePanel()
    {
    }

    protected void initialize()
    {
        player = game.getPlayers().get(0);

        gameBehaviourFactory = new StandardGameBehaviourFactory();

        bankStockPanel = createBankStockPanel();
        buildPallette = createActionsWidget();
        playersWidget = createPlayersWidget();
        gameBoardVisual = createGameBoard(800, 500, game);
        handCards = createHandCardsWidget(player);
        statusPanel = createStatusDicePanel(this);
        tradePlayers = new TradePlayersDialog(this);
        historyWidget = createHistoryWidget(this);
        bankTradeUI = createBankTradeUI(this);

        gameBoardVisual.setBehaviour(new DisabledMap());

        chatPanel = new ChatPanel(this);
        gameQueuePanel = new GameQueuePanel(this);
        debugPanel = new DebugPanel(this);
    }

    /**
     * @return the playersWidget
     */
    public IPlayersWidget getPlayersWidget()
    {
        return playersWidget;
    }

    /**
     * @return the tradePlayers
     */
    public TradePlayersDialog getTradePlayers()
    {
        return tradePlayers;
    }

    @Override
    public Game getGame()
    {
        return game;
    }

    @Override
    public void startAction(GameAction action)
    {
        action.setPlayer(player);
        if (action instanceof TurnAction)
        {
            TurnAction turnAction = (TurnAction) action;
            // Create a behaviour based on our action
            GameBehaviour gameBehaviour = gameBehaviourFactory.createBehaviour(
                    turnAction, game);

            if (gameBehaviour == null)
            {
                // no behaviour found for the action, send the action right away
                server.sendAction(turnAction);
            }
            else
            {
                // Tell our GameVisual it needs to display the behaviour
                gameBoardVisual.setBehaviour(gameBehaviour);

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

    @Override
    public void requestBankTrade(PlayerPiece piece, GamePlayer player)
    {
        bankTradeUI.setPiece(piece);
        bankTradeUI.show();
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.gwtClient.game.abstractWidgets.IGamePanel#getPlayingPlayer()
     */
    @Override
    public GamePlayer getPlayingPlayer()
    {
        return player;
    }

    public void showTradePlayersPanel()
    {
        Point2D location = playersWidget.getTopRightLocation();
        tradePlayers.setPopupPosition(location.getX(), location.getY());
        tradePlayers.show();
    }

    public void hideTradePlayersPanel()
    {
        tradePlayers.hide();
    }

    public void showTradeBankPanel()
    {
        Point2D location = playersWidget.getTopRightLocation();
        bankTradeUI.setPopupPosition(location.getX(), location.getY());
        bankTradeUI.show();
    }

    public void hideTradeBankPanel()
    {
        bankTradeUI.hide();
    }

    /*
     * (non-Javadoc)
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
}
