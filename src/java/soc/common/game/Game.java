package soc.common.game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.event.shared.GwtEvent.Type;

import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.board.Board;
import soc.common.board.HexLocation;
import soc.common.board.resources.Resource;
import soc.common.board.resources.ResourceList;
import soc.common.game.developmentCards.DevelopmentCardList;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.LobbyGamePhase;
import soc.common.game.rules.IRuleSet;

public class Game
{
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

    private SimpleEventBus eventBus = new SimpleEventBus();
    private List<IRuleSet> ruleSets = new ArrayList<IRuleSet>();
    private LinkedList<GamePhase> gamePhases = new LinkedList<GamePhase>();
    private IActionsQueue actionsQueue = new ActionsQueue();
    private ResourceList bank = new ResourceList();
    private int bankAmountPerResource = 19;
    private List<Player> players = new ArrayList<Player>();
    private GameLog gameLog = new GameLog();
    private HexLocation pirate = new HexLocation(0,0);
    private GamePhase currentPhase = new LobbyGamePhase();
    private GameSettings gameSettings = new GameSettings();
    private Player playerOnTurn;
    private Board board;
    private Player gameStarter;
    private List<TurnAction> possibleActions = new ArrayList<TurnAction>();
    private List<Resource> playableResources = new ArrayList<Resource>();
    private DevelopmentCardList developmentCards = new DevelopmentCardList();
    private List<StockItem> stockPieces = new ArrayList<StockItem>();
    private boolean enableLargestArmy;
    
    public void addPlayerOnTurnChangedEventHandler(PlayerOnTurnChangedEventHandler handler)
    {
        eventBus.addHandler(PlayerOnTurnChangedEvent.TYPE, handler);
    }    
    public void addGamePhaseChangedEventHandler(GamePhaseChangedEventHandler handler)
    {
        eventBus.addHandler(GamePhaseChangedEvent.TYPE, handler);
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
    
        // Enables fluent interface usage
        // http://en.wikipedia.org/wiki/Fluent_interface
        return this;
    }

    /**
     * @return the playableResources
     */
    public List<Resource> getPlayableResources()
    {
        return playableResources;
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
        for (Resource resource : playableResources)
        {
            for (int i=0; i< bankAmountPerResource; i++)
            {
                bank.add(resource.Copy());
            }
        }
    }
    /**
     * @return the stockPieces
     */
    public List<StockItem> getStockPieces()
    {
        return stockPieces;
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
     * @return the possibleActions
     */
    public List<TurnAction> getPossibleActions()
    {
        return possibleActions;
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

    public GameLog getGameLog()
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
    public Game setBankAmountPerResource(int bankAmountPerResource)
    {
        this.bankAmountPerResource = bankAmountPerResource;
        
        // Enables fluent interface usage
        // http://en.wikipedia.org/wiki/Fluent_interface
        return this;
    }
    public int getBankAmountPerResource()
    {
        return bankAmountPerResource;
    }  
}
