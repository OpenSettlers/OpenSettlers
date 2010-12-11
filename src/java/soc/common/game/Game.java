package soc.common.game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.google.gwt.event.shared.SimpleEventBus;

import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.board.Board;
import soc.common.board.HexLocation;
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
import soc.common.game.rules.IRuleSet;

public class Game
{
    private SimpleEventBus eventBus = new SimpleEventBus();
    
    private LinkedList<GamePhase> gamePhases = new LinkedList<GamePhase>();
    private IActionsQueue actionsQueue = new ActionsQueue();
    private IGameLog gameLog = new GameLog();
    private IChatLog chatLog = new ChatLog();
    
    private IGameRules gameRules = new GameRules();

    private List<IRuleSet> ruleSets = new ArrayList<IRuleSet>();

    private ResourceList bank = new ResourceList();
    
    private List<Player> players = new ArrayList<Player>();
    private HexLocation pirate = new HexLocation(0,0);
    private HexLocation robber = new HexLocation(0,0);
    private GamePhase currentPhase = new LobbyGamePhase();
    private GameSettings gameSettings = new GameSettings();
    private Player playerOnTurn;
    private Board board;
    private Player gameStarter;
    private DevelopmentCardList developmentCards = new DevelopmentCardList();
    private boolean enableLargestArmy;
    
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
     * @param ruleSets the ruleSets to set
     */
    public Game setRuleSets(List<IRuleSet> ruleSets)
    {
        this.ruleSets = ruleSets;
    
        // Enables fluent interface usage
        // http://en.wikipedia.org/wiki/Fluent_interface
        return this;
    }
    
    public void addPlayerOnTurnChangedEventHandler(PlayerOnTurnChangedEventHandler handler)
    {
        eventBus.addHandler(PlayerOnTurnChangedEvent.TYPE, handler);
    }    
    public void addGamePhaseChangedEventHandler(GamePhaseChangedEventHandler handler)
    {
        eventBus.addHandler(GamePhaseChangedEvent.TYPE, handler);
    }
    
    /**
     * @return the robber
     */
    public HexLocation getRobber()
    {
        return robber;
    }
    /**
     * @param robber the robber to set
     */
    public Game setRobber(HexLocation robber)
    {
        this.robber = robber;
    
        return this;
    }
    /**
     * @return the developmentCards
     */
    public DevelopmentCardList getDevelopmentCards()
    {
        return developmentCards;
    }

    /**
     * @param developmentCards the developmentCards to set
     */
    public Game setDevelopmentCards(DevelopmentCardList developmentCards)
    {
        this.developmentCards = developmentCards;
    
        return this;
    }
    public void makeRulesPermanent()
    {
        for (IRuleSet ruleset : ruleSets)
        {
            ruleset.setRules();
        }
        
        createBank();
    }
    
    private void createBank()
    {
        for (Resource resource : gameRules.getPlayableResources())
        {
            for (int i=0; i< gameRules.getBankAmountPerResource(); i++)
            {
                bank.add(resource.Copy());
            }
        }
    }

    /**
     * @return the enableLargestArmy
     */
    public boolean isEnableLargestArmy()
    {
        return enableLargestArmy;
    }
    /**
     * @param enableLargestArmy the enableLargestArmy to set
     */
    public Game setEnableLargestArmy(boolean enableLargestArmy)
    {
        this.enableLargestArmy = enableLargestArmy;
    
        // Enables fluent interface usage
        // http://en.wikipedia.org/wiki/Fluent_interface
        return this;
    }
    /**
     * @return the ruleSets
     */
    public List<IRuleSet> getRuleSets()
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
     * @param gameStarter the gameStarter to set
     */
    public Game setGameStarter(Player gameStarter)
    {
        this.gameStarter = gameStarter;
    
        // Enables fluent interface usage
        // http://en.wikipedia.org/wiki/Fluent_interface
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
     * @param board the board to set
     */
    public Game setBoard(Board board)
    {
        this.board = board;
    
        // Enables fluent interface usage
        // http://en.wikipedia.org/wiki/Fluent_interface
        return this;
    }
    public Player getPlayerByID(int id)
    {
        for (Player p : players)
        {
            if (p.getId() == id)
                return p;
        }
        throw new RuntimeException(
                "Trying to get non-existing player. ID " + id + " is unknown");
    }
    /**
     * @return the playerOnTurn
     */
    public Player getPlayerOnTurn()
    {
        if (playerOnTurn == null)
        {
            playerOnTurn = players.get(0);
        }
        return playerOnTurn;
    }

    /**
     * @param playerOnTurn the playerOnTurn to set
     */
    public Game setPlayerOnTurn(Player playerOnTurn)
    {
        // Create an event 
        PlayerOnTurnChangedEvent event = new PlayerOnTurnChangedEvent(this.playerOnTurn, playerOnTurn);
        
        // Set the new values
        playerOnTurn.setOnTurn(false);
        this.playerOnTurn = playerOnTurn;
        playerOnTurn.setOnTurn(true);
        
        // fire the event
        eventBus.fireEvent(event);
    
        return this;
    }

    /**
     * @return the gameSettings
     */
    public GameSettings getGameSettings()
    {
        return gameSettings;
    }

    /**
     * @param gameSettings the gameSettings to set
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
    
    public Game(List<IRuleSet> ruleSets)
    {
        this.ruleSets = ruleSets;
        
        for (IRuleSet ruleSet : ruleSets)
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
        GamePhaseChangedEvent event = new GamePhaseChangedEvent(this.currentPhase, currentPhase);
        
        this.currentPhase = currentPhase;
        eventBus.fireEvent(event);
        
        return this;
    }
    
    public Player getNextPlayer()
    {
        int index = players.indexOf(playerOnTurn) + 1;
        if (index == players.size())
        {
            index = 0;
        }
        return players.get(index);
    }

    public Game copy()
    {
        return null;
    }  
}
