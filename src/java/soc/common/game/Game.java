package soc.common.game;

import java.util.ArrayList;
import java.util.List;

import soc.common.actions.Action;
import soc.common.actions.gameAction.GameAction;
import soc.common.board.Board;
import soc.common.board.HexLocation;
import soc.common.board.hexes.DesertHex;
import soc.common.board.hexes.Hex;
import soc.common.board.pieces.Army;
import soc.common.board.pieces.LongestRoad;
import soc.common.board.pieces.Pirate;
import soc.common.board.pieces.PlayerPiece;
import soc.common.board.pieces.PlayerPieceList;
import soc.common.board.pieces.PointPiece;
import soc.common.board.pieces.Robber;
import soc.common.board.resources.ResourceList;
import soc.common.game.developmentCards.DevelopmentCardList;
import soc.common.game.dices.Dice;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.LobbyGamePhase;
import soc.common.game.gamePhase.PlayTurnsGamePhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;
import soc.common.game.gamePhase.turnPhase.TurnPhaseChangedEvent;
import soc.common.game.gamePhase.turnPhase.TurnPhaseChangedHandler;
import soc.common.game.logs.ActionsQueue;
import soc.common.game.logs.ActionsQueueImpl;
import soc.common.game.logs.ChatLog;
import soc.common.game.logs.ChatLogImpl;
import soc.common.game.logs.GameLog;
import soc.common.game.logs.GameLogImpl;
import soc.common.game.logs.QueuedAction;
import soc.common.game.player.GamePlayer;
import soc.common.game.player.GamePlayerList;
import soc.common.game.statuses.GameStatus;
import soc.common.game.statuses.Playing;

import com.google.gwt.event.shared.HandlerRegistration;
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

    private GamePlayerList players = new GamePlayerList();
    private List<GamePlayer> spectators = new ArrayList<GamePlayer>();

    // Game rules specific stuff
    private GameSettings gameSettings = new GameSettings();
    private Board board;
    private ResourceList bank = new ResourceList();
    private DevelopmentCardList developmentCardStack = new DevelopmentCardList();
    private Pirate pirate = null;
    private Robber robber = null;
    private Army largestArmy;
    private LongestRoad longestRoute;
    private Dice currentDice;
    private GamePlayer gameStarter;

    // State
    private GamePhase currentPhase = new LobbyGamePhase();
    private Turn currentTurn;
    private GameStatus currentStatus = new Playing();

    public Game()
    {
        gameRules = new GameRulesImpl(this);
    }

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

    public boolean advanceTurnPhase()
    {
        PlayTurnsGamePhase playTurns = (PlayTurnsGamePhase) currentPhase;
        TurnPhase oldPhase = playTurns.getTurnPhase();
        TurnPhase newPhase = playTurns.getTurnPhase().next();
        if (!oldPhase.equals(newPhase))
        {
            playTurns.setTurnPhase(newPhase);
            eventBus.fireEvent(new TurnPhaseChangedEvent(newPhase, oldPhase));
            return true;
        }
        else
        {
            return false;
        }
    }

    public void advanceTurn()
    {
        Turn newTurn = currentPhase.nextTurn(this);

        currentTurn.getPlayer().setOnTurn(false);
        newTurn.getPlayer().setOnTurn(true);

        Turn oldTurn = currentTurn;
        this.currentTurn = newTurn;

        // Notify handlers the current turn is changed
        eventBus.fireEvent(new TurnChangedEvent(oldTurn, newTurn));
    }

    public void start()
    {
        gameRules.setRules(this);
        board.prepareForPlay(gameSettings);
    }

    /*
     * Returns a list of all PlayerPieces in the game residing on a HexSide
     */
    public PlayerPieceList getAllSidePieces()
    {
        PlayerPieceList result = new PlayerPieceList();

        for (GamePlayer player : players)
            result.add(player.getBuildPieces().getSidePieces());

        return result;
    }

    public void updateStatus()
    {
        GameStatus newStatus = null;

        if (actionsQueue.isWaitingForActions())
        {
            // newStatus = new WaitingForTurnActions()
            // .setBlockingActions(actionsQueue.getBlockingActions());
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
            if (currentStatus.isGameBlocking())
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

    public GamePlayer getPlayerByID(int id)
    {
        for (GamePlayer p : players)
        {
            if (p.getUser().getId() == id)
                return p;
        }

        return null;
    }

    /*
     * Returns a list of GamePlayers having a town or city at given HexLocation
     */
    public GamePlayerList getPlayersAtHex(HexLocation hexLocation,
            GamePlayer skipPlayer)
    {
        GamePlayerList playersAtHex = new GamePlayerList();

        // Check all opponents
        for (GamePlayer player : players.getOpponents(skipPlayer))
        {
            // If a PointPiece has given HexLocation contained, add the player
            // to the list of players having a town or city at given HexLocation
            for (PlayerPiece playerPiece : player.getBuildPieces()
                    .getPointPieces())
            {
                PointPiece pointPiece = (PointPiece) playerPiece;
                if (pointPiece.getPoint().hasLocation(hexLocation))
                {
                    playersAtHex.add(player);
                }
            }
        }

        return playersAtHex;
    }

    public void advanceGamePhase()
    {
        GamePhase newGamePhase = currentPhase.next(this);
        GamePhaseChangedEvent event = new GamePhaseChangedEvent(currentPhase,
                newGamePhase);

        currentPhase = newGamePhase;

        // Fire a TurnPhaseChangedEvent when setting new phase to
        // PlayTurnsGamePhase
        if (currentPhase instanceof PlayTurnsGamePhase)
        {
            eventBus.fireEvent(new TurnPhaseChangedEvent(
                    ((PlayTurnsGamePhase) currentPhase).getTurnPhase(), null));
        }

        // Start the next phase
        currentPhase.start(this);

        eventBus.fireEvent(event);
    }

    public void performAction(GameAction action)
    {
        QueuedAction expectedAction = actionsQueue.findExpected(action, this);

        if (expectedAction != null)
            actionsQueue.dequeue(expectedAction);

        currentPhase.performAction(action, this);
    }

    public void initialize()
    {
        // Set first player on turn
        currentTurn = new TurnImpl().setPlayer(players.get(0));

        // Move robber to desert hex, if exists
        Hex desertHex = null;
        for (Hex hex : board.getHexes())
        {
            if (hex instanceof DesertHex)
                desertHex = hex;
        }

        if (desertHex != null)
        {
            robber.setLocation(desertHex.getLocation());
        }

        currentStatus = new Playing();
    }

    /*
     * Switches largest army to specified player
     */
    public void switchLargestArmyIfNeeded(GamePlayer player)
    {
        // Only check largest army update when player has more then 2
        // soldiers
        if (player.getArmy().getSoldiersAmount() > 2)
        {
            // Remove largest army from current player, if player holds one
            GamePlayer opponent = largestArmy.getPlayer();
            if (opponent == null)
            {
                // No one owns LA yet, set it to given player
                player.getArmy().addToPlayer(player);
                largestArmy.setPlayer(player);
            }
            else
            {
                // There is an opponent with a bigger army
                if (!opponent.equals(player)
                        && opponent.getArmy().getSoldiersAmount() < player
                                .getArmy().getSoldiersAmount())
                {
                    // Player wins LA from previous player
                    opponent.getArmy().removeFromPlayer(opponent);
                    player.getArmy().addToPlayer(player);
                    largestArmy.setPlayer(player);
                }
            }
        }
    }

    public HandlerRegistration addGamePhaseChangedEventHandler(
            GamePhaseChangedEventHandler handler)
    {
        return eventBus.addHandler(GamePhaseChangedEvent.TYPE, handler);
    }

    public HandlerRegistration addDiceChangedEventHandler(
            DiceChangedEventHandler handler)
    {
        return eventBus.addHandler(DiceChangedEvent.TYPE, handler);
    }

    public HandlerRegistration addTurnchangedeventHandler(
            TurnChangedEventHandler handler)
    {
        return eventBus.addHandler(TurnChangedEvent.TYPE, handler);
    }

    public HandlerRegistration addTurnPhaseChangedHandler(
            TurnPhaseChangedHandler handler)
    {
        return eventBus.addHandler(TurnPhaseChangedEvent.TYPE, handler);
    }

    public HandlerRegistration addStatusChangedEventHandler(
            StatusChangedEventHandler handler)
    {
        return eventBus.addHandler(StatusChangedEvent.TYPE, handler);
    }

    public Dice getCurrentDice()
    {
        return currentDice;
    }

    public Game setCurrentDice(Dice currentDice)
    {
        this.currentDice = currentDice;

        eventBus.fireEvent(new DiceChangedEvent(currentDice));

        return this;
    }

    public Board getBoard()
    {
        return board;
    }

    public Game setBoard(Board board)
    {
        this.board = board;

        return this;
    }

    public GameStatus getStatus()
    {
        return currentStatus;
    }

    public void setStatus(GameStatus newStatus)
    {
        GameStatus oldStatus = currentStatus;
        currentStatus = newStatus;

        eventBus.fireEvent(new StatusChangedEvent(newStatus, oldStatus));
    }

    public Turn getCurrentTurn()
    {
        return currentTurn;
    }

    public GameRules getRules()
    {
        return gameRules;
    }

    public ChatLog getChatLog()
    {
        return chatLog;
    }

    public Robber getRobber()
    {
        return robber;
    }

    public Game setRobber(Robber robber)
    {
        this.robber = robber;

        return this;
    }

    public DevelopmentCardList getDevelopmentCardStack()
    {
        return developmentCardStack;
    }

    public Game setDevelopmentCards(DevelopmentCardList developmentCards)
    {
        this.developmentCardStack = developmentCards;

        return this;
    }

    public GamePlayer getStartPlayer()
    {
        return gameStarter;
    }

    public Game setStartPlayer(GamePlayer gameStarter)
    {
        this.gameStarter = gameStarter;

        players.setStartPlayer(gameStarter);

        return this;
    }

    public Game setDevelopmentCardStack(DevelopmentCardList developmentCardStack)
    {
        this.developmentCardStack = developmentCardStack;

        return this;
    }

    public Army getLargestArmy()
    {
        return largestArmy;
    }

    public Game setLargestArmy(Army largestArmy)
    {
        this.largestArmy = largestArmy;

        return this;
    }

    public LongestRoad getLongestRoute()
    {
        return longestRoute;
    }

    public Game setLongestRoute(LongestRoad longestRoute)
    {
        this.longestRoute = longestRoute;

        return this;
    }

    public GameSettings getGameSettings()
    {
        return gameSettings;
    }

    public void setGameSettings(GameSettings gameSettings)
    {
        this.gameSettings = gameSettings;
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

    public GamePlayerList getPlayers()
    {
        return players;
    }

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
}
