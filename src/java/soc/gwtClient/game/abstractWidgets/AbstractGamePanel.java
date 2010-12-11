package soc.gwtClient.game.abstractWidgets;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.board.pieces.PlayerPiece;
import soc.common.client.behaviour.StandardGameBehaviourFactory;
import soc.common.client.behaviour.IGameBehaviourFactory;
import soc.common.client.behaviour.game.IGameBehaviour;
import soc.common.client.visuals.game.IGameBoardVisual;
import soc.common.game.Game;
import soc.common.game.Player;
import soc.common.server.HotSeatServer;
import soc.common.server.IGameServer;
import soc.common.server.IGameServerCallback;
import soc.gwtClient.game.ICenterWidget;
import soc.gwtClient.game.dialogs.NewGameDialog;

public abstract class AbstractGamePanel 
    implements IGamePanel, ICenterWidget, IGameServerCallback
{
    protected IGameServer server;
    protected Game game;
    protected NewGameDialog newGameWindow;
    protected IActionsWidget buildPallette;
    protected IBankStockPanel bankStockPanel;
    protected IBankTradeUI bankTradeUI;
    protected IGameBehaviourFactory gameBehaviourFactory;
    protected IGameBoardVisual gameVisual;
    protected GameAction performingAction;
    protected IPlayersWidget playersWidget;
    protected IGameBoardVisual gameBoard;
    protected Player player;
    
    public AbstractGamePanel(Game game)
    {
        this.game=game;
        
        
        gameBehaviourFactory = new StandardGameBehaviourFactory();
        
        bankStockPanel = createBankStockPanel();
        buildPallette = createActionsWidget();
        playersWidget = createPlayersWidget();
        gameBoard = createGameBoard(500,500, game.getBoard());
    }
    
    /* (non-Javadoc)
     * @see soc.common.server.IGameServerCallback#receive(soc.common.actions.gameAction.GameAction)
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
    public void startAction(GameAction action)
    {
        if (action instanceof TurnAction)
        {
            TurnAction turnAction = (TurnAction)action;
            // Create a behaviour based on our action
            IGameBehaviour gameBehaviour = gameBehaviourFactory.createBehaviour(turnAction, game);
            
            if (gameBehaviour == null)
            {
                // no behaviour found for the action, send the action right away
                server.sendAction(turnAction);
            }
            else
            {
                // Tell our GameVisual it needs to display the behaviour
                gameVisual.setBehaviour(gameBehaviour);
                
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
    public void requestBankTrade(PlayerPiece piece, Player player)
    {
        bankTradeUI.setPiece(piece);
        bankTradeUI.show();
    }

    /* (non-Javadoc)
     * @see soc.gwtClient.game.abstractWidgets.IGamePanel#getPlayingPlayer()
     */
    @Override
    public Player getPlayingPlayer()
    {
        return player;
    }
}
