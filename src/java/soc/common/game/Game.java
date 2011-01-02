package soc.common.game;

import java.util.ArrayList;
import java.util.List;

import soc.common.actions.Action;
import soc.common.actions.gameAction.GameAction;
import soc.common.board.HexLocation;
import soc.common.board.pieces.LargestArmy;
import soc.common.board.pieces.LongestRoad;
import soc.common.board.pieces.Pirate;
import soc.common.board.pieces.PlayerPieceList;
import soc.common.board.pieces.Robber;
import soc.common.board.resources.ResourceList;
import soc.common.game.developmentCards.DevelopmentCardList;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.LobbyGamePhase;
import soc.common.game.logs.ActionsQueue;
import soc.common.game.logs.ActionsQueueImpl;
import soc.common.game.logs.ChatLog;
import soc.common.game.logs.ChatLogImpl;
import soc.common.game.logs.GameLog;
import soc.common.game.logs.GameLogImpl;
import soc.common.game.statuses.GameStatus;
import soc.common.game.statuses.WaitingForPlayers;
import soc.common.game.statuses.WaitingForTurnActions;

import com.google.gwt.event.shared.SimpleEventBus;

public class Game
{
    // Event subscribers/broadcasting mechanism
    private transient SimpleEventBus eventBus = new SimpleEventBus();

    // Lists of actions, logs & future actions
    private ActionsQueue actionsQueue = new ActionsQueueImpl();
    private GameLog gameLog = new GameLogImpl();
    private ChatLog chatLog = new ChatLogImpl();

    // Abstracted rules
    private GameRules gameRules;

    private ResourceList bank = new ResourceList();

    private List<GamePlayer> players = new ArrayList<GamePlayer>();
    private List<GamePlayer> spectators = new ArrayList<GamePlayer>();
    private Pirate pirate = new Pirate(new HexLocation(0, 0));
    private Robber robber = null;
    private GamePhase currentPhase = new LobbyGamePhase();
    private GameSettings gameSettings = new GameSettings();
    private GameBoard gameBoard;
    private GamePlayer gameStarter;
    private DevelopmentCardList developmentCardStack = new DevelopmentCardList();
    private Turn currentTurn;
    private GameStatus currentStatus = new WaitingForPlayers();
    private LargestArmy largestArmy;
    private LongestRoad longestRoute;

    /*
     * Returns a list of all PlayerPieces in the game residing on a HexPoint
     */
    public PlayerPieceList getAllPointPieces()
    {
        PlayerPieceList result = new PlayerPieceList();

        for (GamePlayer player : players)
        {
            result.add(player.getBuildPieces().getPointPieces());
        }

        return result;
    }

    public void advanceTurn()
    {
        // Determine index of next player
        int nextPlayerIndex = players.indexOf(currentTurn.getPlayer()) + 1;
        if (nextPlayerIndex == players.size())
        {
            nextPlayerIndex = 0;
        }

        GamePlayer newPlayerOnTurn = players.get(nextPlayerIndex);

        // Create a new turn
        Turn newTurn = new TurnImpl().setID(currentTurn.getID() + 1).setPlayer(
                newPlayerOnTurn);

        currentTurn.getPlayer().setOnTurn(false);
        newPlayerOnTurn.setOnTurn(true);

        Turn oldTurn = currentTurn;
        this.currentTurn = newTurn;

        // Notify handlers the current turn is changed
        eventBus.fireEvent(new TurnChangedEvent(oldTurn, newTurn));
    }

    public void start()
    {
        gameRules.setRules(this);
        gameBoard.prepareForPlay(gameSettings);
    }

    /*
     * Returns a list of all PlayerPieces in the game residing on a HexSide
     */
    public PlayerPieceList getAllSidePieces()
    {
        PlayerPieceList result = new PlayerPieceList();

        for (GamePlayer player : players)
        {
            result.add(player.getBuildPieces().getSidePieces());
        }

        return result;
    }

    public void updateStatus()
    {
        GameStatus newStatus = null;

        if (actionsQueue.isWaitingForActions())
        {
            newStatus = new WaitingForTurnActions()
                    .setBlockingActions(actionsQueue.getBlockingActions());
        }

        currentStatus = newStatus;
    }

    /*
     * Returns true when given action is allowed to be performed
     */
    public boolean isAllowed(Action action)
    {
        if (action instanceof GameAction)
        {
            GameAction gameAction = (GameAction) action;

            // Check if the status allows the action
            if (!gameAction.isAllowed(currentStatus))
            {
                return false;
            }

            // Check if the current phase allows the action
            if (!currentPhase.isAllowed(gameAction))
            {
                return false;
            }
        }

        return true;
    }

    /**
     * @return the gameStatus
     */
    public GameStatus getGameStatus()
    {
        return currentStatus;
    }

    /**
     * @return the currentTurn
     */
    public Turn getCurrentTurn()
    {
        return currentTurn;
    }

    /**
     * @return the gameRules
     */
    public GameRules getGameRules()
    {
        return gameRules;
    }

    /**
     * @return the chatLog
     */
    public ChatLog getChatLog()
    {
        return chatLog;
    }

    public void addGamePhaseChangedEventHandler(
            GamePhaseChangedEventHandler handler)
    {
        eventBus.addHandler(GamePhaseChangedEvent.TYPE, handler);
    }

    public void addDiceChangedEventHandler(DiceChangedEventHandler handler)
    {
        eventBus.addHandler(DiceChangedEvent.TYPE, handler);
    }

    /**
     * @return the robber
     */
    public Robber getRobber()
    {
        return robber;
    }

    /**
     * @param robber
     *            the robber to set
     */
    public Game setRobber(Robber robber)
    {
        this.robber = robber;

        return this;
    }

    /**
     * @return the developmentCards
     */
    public DevelopmentCardList getDevelopmentCardStack()
    {
        return developmentCardStack;
    }

    /**
     * @param developmentCards
     *            the developmentCards to set
     */
    public Game setDevelopmentCards(DevelopmentCardList developmentCards)
    {
        this.developmentCardStack = developmentCards;

        return this;
    }

    /**
     * @return the gameStarter
     */
    public GamePlayer getGameStarter()
    {
        return gameStarter;
    }

    /**
     * @param gameStarter
     *            the gameStarter to set
     */
    public Game setGameStarter(GamePlayer gameStarter)
    {
        this.gameStarter = gameStarter;

        return this;
    }

    /**
     * @return the board
     */
    public GameBoard getBoard()
    {
        return gameBoard;
    }

    /**
     * @param board
     *            the board to set
     */
    public Game setBoard(GameBoard gameBoard)
    {
        this.gameBoard = gameBoard;

        return this;
    }

    public GamePlayer getPlayerByID(int id)
    {
        for (GamePlayer p : players)
        {
            if (p.getUser().getId() == id)
                return p;
        }
        throw new RuntimeException("Trying to get non-existing player. ID "
                + id + " is unknown");
    }

    /**
     * @return the gameSettings
     */
    public GameSettings getGameSettings()
    {
        return gameSettings;
    }

    /**
     * @param gameSettings
     *            the gameSettings to set
     */
    public void setGameSettings(GameSettings gameSettings)
    {
        this.gameSettings = gameSettings;
    }

    /*
     * Parameterless deserialization constructor
     */
    public Game()
    {
        gameRules = new GameRulesImpl(this);
    }

    public ResourceList getBank()
    {
        return bank;
    }

    public void setBank(ResourceList bank)
    {
        this.bank = bank;
    }

    public ActionsQueue getActionsQueue()
    {
        return actionsQueue;
    }

    public void setActionsQueue(ActionsQueue actionsQueue)
    {
        this.actionsQueue = actionsQueue;
    }

    public List<GamePlayer> getPlayers()
    {
        return players;
    }

    /**
     * @return the spectators
     */
    public List<GamePlayer> getSpectators()
    {
        return spectators;
    }

    public GameLog getGameLog()
    {
        return gameLog;
    }

    public void setGameLog(GameLogImpl gameLog)
    {
        this.gameLog = gameLog;
    }

    public Pirate getPirate()
    {
        return pirate;
    }

    public void setPirate(Pirate pirate)
    {
        this.pirate = pirate;
    }

    public GamePhase getCurrentPhase()
    {
        return currentPhase;
    }

    public Game setCurrentPhase(GamePhase currentPhase)
    {
        GamePhaseChangedEvent event = new GamePhaseChangedEvent(
                this.currentPhase, currentPhase);

        this.currentPhase = currentPhase;
        eventBus.fireEvent(event);

        return this;
    }

    public Game copy()
    {
        return null;
    }

    /**
     * @return the largestArmy
     */
    public LargestArmy getLargestArmy()
    {
        return largestArmy;
    }

    /**
     * @param largestArmy
     *            the largestArmy to set
     */
    public Game setLargestArmy(LargestArmy largestArmy)
    {
        this.largestArmy = largestArmy;

        return this;
    }

    /**
     * @return the longestRoute
     */
    public LongestRoad getLongestRoute()
    {
        return longestRoute;
    }

    /**
     * @param longestRoute
     *            the longestRoute to set
     */
    public Game setLongestRoute(LongestRoad longestRoute)
    {
        this.longestRoute = longestRoute;

        return this;
    }

    /**
     * @param developmentCardStack
     *            the developmentCardStack to set
     */
    public Game setDevelopmentCardStack(DevelopmentCardList developmentCardStack)
    {
        this.developmentCardStack = developmentCardStack;

        return this;
    }

}
