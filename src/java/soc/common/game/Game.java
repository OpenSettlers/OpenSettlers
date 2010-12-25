package soc.common.game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import soc.common.actions.Action;
import soc.common.actions.gameAction.GameAction;
import soc.common.board.Board;
import soc.common.board.HexLocation;
import soc.common.board.pieces.PlayerPieceList;
import soc.common.board.resources.Resource;
import soc.common.board.resources.ResourceList;
import soc.common.game.developmentCards.DevelopmentCardList;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.LobbyGamePhase;
import soc.common.game.logs.ActionsQueue;
import soc.common.game.logs.ChatLog;
import soc.common.game.logs.GameLog;
import soc.common.game.logs.IActionsQueue;
import soc.common.game.logs.IChatLog;
import soc.common.game.logs.IGameLog;
import soc.common.game.statuses.IGameStatus;
import soc.common.game.statuses.WaitingForPlayers;
import soc.common.game.statuses.WaitingForTurnActions;
import soc.common.game.variants.IVariant;

import com.google.gwt.event.shared.SimpleEventBus;

public class Game
{
    // Event subscribers/broadcasting mechanism
    private SimpleEventBus eventBus = new SimpleEventBus();

    private LinkedList<GamePhase> gamePhases = new LinkedList<GamePhase>();

    // Lists of actions, logs & future actions
    private IActionsQueue actionsQueue = new ActionsQueue();
    private IGameLog gameLog = new GameLog();
    private IChatLog chatLog = new ChatLog();

    // Abstracted rules
    private IGameRules gameRules = new GameRules();

    private List<IVariant> ruleSets = new ArrayList<IVariant>();

    private ResourceList bank = new ResourceList();

    private List<Player> players = new ArrayList<Player>();
    private HexLocation pirate = new HexLocation(0, 0);
    private HexLocation robber = new HexLocation(0, 1);
    private GamePhase currentPhase = new LobbyGamePhase();
    private GameSettings gameSettings = new GameSettings();
    private Board board;
    private Player gameStarter;
    private DevelopmentCardList developmentCardStack = new DevelopmentCardList();
    private Turn currentTurn;
    private IGameStatus currentStatus = new WaitingForPlayers();

    /*
     * Returns a list of all PlayerPieces in the game residing on a HexPoint
     */
    public PlayerPieceList getAllPointPieces()
    {
        PlayerPieceList result = new PlayerPieceList();

        for (Player player : players)
        {
            result.add(player.getBuildPieces().getPointPieces());
        }

        return result;
    }

    /*
     * Returns a list of all PlayerPieces in the game residing on a HexSide
     */
    public PlayerPieceList getAllSidePieces()
    {
        PlayerPieceList result = new PlayerPieceList();

        for (Player player : players)
        {
            result.add(player.getBuildPieces().getSidePieces());
        }

        return result;
    }

    public void updateStatus()
    {
        IGameStatus newStatus = null;

        if (actionsQueue.isWaitingForActions())
        {
            newStatus = new WaitingForTurnActions()
                    .setBlockingActions(actionsQueue.getBlockingActions());
        }

        currentStatus = newStatus;
    }

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
    public IGameStatus getGameStatus()
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
    public IGameRules getGameRules()
    {
        return gameRules;
    }

    /**
     * @return the chatLog
     */
    public IChatLog getChatLog()
    {
        return chatLog;
    }

    /**
     * @param ruleSets
     *            the ruleSets to set
     */
    public Game setRuleSets(List<IVariant> ruleSets)
    {
        this.ruleSets = ruleSets;

        return this;
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
    public HexLocation getRobber()
    {
        return robber;
    }

    /**
     * @param robber
     *            the robber to set
     */
    public Game setRobber(HexLocation robber)
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

    public void makeRulesPermanent()
    {
        for (IVariant ruleset : ruleSets)
        {
            ruleset.setRules();
        }

        createBank();
    }

    /*
     * Creates a bank. Adds X amount of resources per resource type found in the
     * list of playable resources, where X is amount per resource
     */
    private void createBank()
    {
        for (Resource resource : gameRules.getSupportedResources())
        {
            for (int i = 0; i < gameRules.getBankAmountPerResource(); i++)
            {
                bank.add(resource.copy());
            }
        }
    }

    /**
     * @return the ruleSets
     */
    public List<IVariant> getRuleSets()
    {
        return ruleSets;
    }

    /**
     * @return the gameStarter
     */
    public Player getGameStarter()
    {
        return gameStarter;
    }

    /**
     * @param gameStarter
     *            the gameStarter to set
     */
    public Game setGameStarter(Player gameStarter)
    {
        this.gameStarter = gameStarter;

        return this;
    }

    /**
     * @return the board
     */
    public Board getBoard()
    {
        return board;
    }

    /**
     * @param board
     *            the board to set
     */
    public Game setBoard(Board board)
    {
        this.board = board;

        return this;
    }

    public Player getPlayerByID(int id)
    {
        for (Player p : players)
        {
            if (p.getId() == id)
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
    }

    public Game(List<IVariant> ruleSets)
    {
        this.ruleSets = ruleSets;

        for (IVariant ruleSet : ruleSets)
        {
            // Initialize the list of pieces allowed to be built
            ruleSet.addBuildablePieces();
        }

    }

    public ResourceList getBank()
    {
        return bank;
    }

    public void setBank(ResourceList bank)
    {
        this.bank = bank;
    }

    public LinkedList<GamePhase> getGamePhases()
    {
        return gamePhases;
    }

    public void setGamePhases(LinkedList<GamePhase> gamePhases)
    {
        this.gamePhases = gamePhases;
    }

    public IActionsQueue getActionsQueue()
    {
        return actionsQueue;
    }

    public void setActionsQueue(IActionsQueue actionsQueue)
    {
        this.actionsQueue = actionsQueue;
    }

    public List<Player> getPlayers()
    {
        return players;
    }

    public void setPlayers(List<Player> players)
    {
        this.players = players;
    }

    public IGameLog getGameLog()
    {
        return gameLog;
    }

    public void setGameLog(GameLog gameLog)
    {
        this.gameLog = gameLog;
    }

    public HexLocation getPirate()
    {
        return pirate;
    }

    public void setPirate(HexLocation pirate)
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

    /*
     * Advances turn to the next player in sequence
     */
    public void nextTurn()
    {
        /*
         * int index = players.indexOf(playerOnTurn) + 1; if (index ==
         * players.size()) { index = 0; } return players.get(index);
         */
    }

    public Game copy()
    {
        return null;
    }
}
