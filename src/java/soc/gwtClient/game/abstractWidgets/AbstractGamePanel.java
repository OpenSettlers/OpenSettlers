package soc.gwtClient.game.abstractWidgets;

import soc.common.actions.gameAction.AbstractGameAction;
import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.turnActions.AbstractTurnAction;
import soc.common.board.pieces.PlayerPiece;
import soc.common.client.behaviour.GameBehaviourFactory;
import soc.common.client.behaviour.StandardGameBehaviourFactory;
import soc.common.client.behaviour.game.GameBehaviour;
import soc.common.client.visuals.game.GameBoardVisual;
import soc.common.game.Game;
import soc.common.game.GamePlayer;
import soc.common.server.GameServer;
import soc.common.server.IGameServerCallback;
import soc.gwtClient.game.ICenterWidget;
import soc.gwtClient.game.Point2D;
import soc.gwtClient.game.dialogs.NewGameDialog;
import soc.gwtClient.game.dialogs.TradePlayersDialog;

public abstract class AbstractGamePanel implements IGamePanel, ICenterWidget,
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
    protected AbstractGameAction performingAction;
    protected IPlayersWidget playersWidget;
    protected GamePlayer player;
    protected HandCardsWidget handCards;
    protected StatusPanel statusPanel;
    protected TradePlayersDialog tradePlayers;
    protected GameHistoryWidget historyWidget;

    public AbstractGamePanel(Game game)
    {
        this.game = game;
        player = game.getPlayers().get(0);

        gameBehaviourFactory = new StandardGameBehaviourFactory();

        bankStockPanel = createBankStockPanel();
        buildPallette = createActionsWidget();
        playersWidget = createPlayersWidget();
        gameBoardVisual = createGameBoard(500, 500, game);
        handCards = createHandCardsWidget(player);
        statusPanel = createStatusDicePanel(this);
        tradePlayers = new TradePlayersDialog(this);
        historyWidget = createHistoryWidget(this);
        bankTradeUI = createBankTradeUI(this);
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

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.server.IGameServerCallback#receive(soc.common.actions.gameAction
     * .GameAction)
     */
    @Override
    public void receive(GameAction action)
    {
        // perform the action
        game.getCurrentPhase().performAction(action, game);
    }

    @Override
    public Game getGame()
    {
        return game;
    }

    @Override
    public void startAction(AbstractGameAction action)
    {
        if (action instanceof AbstractTurnAction)
        {
            AbstractTurnAction turnAction = (AbstractTurnAction) action;
            // Create a behaviour based on our action
            GameBehaviour gameBehaviour = gameBehaviourFactory
                    .createBehaviour(turnAction, game);

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
}
