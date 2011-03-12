package soc.common.game;

import java.io.Serializable;
import java.util.Date;

import soc.common.actions.Action;
import soc.common.actions.gameAction.GameAction;
import soc.common.board.Board;
import soc.common.board.hexes.DesertHex;
import soc.common.board.hexes.Hex;
import soc.common.board.layout.HasPoint;
import soc.common.board.layout.HexLocation;
import soc.common.board.pieces.Army;
import soc.common.board.pieces.LongestRoad;
import soc.common.board.pieces.Pirate;
import soc.common.board.pieces.Robber;
import soc.common.board.pieces.abstractPieces.BoardPiece;
import soc.common.board.pieces.abstractPieces.PlayerPiece;
import soc.common.board.pieces.pieceLists.PointPieceList;
import soc.common.board.pieces.pieceLists.SidePieceList;
import soc.common.board.resources.ResourceList;
import soc.common.board.routing.Route;
import soc.common.game.developmentCards.DevelopmentCardList;
import soc.common.game.dices.Dice;
import soc.common.game.dices.DiceChangedEvent;
import soc.common.game.dices.DiceChangedEventHandler;
import soc.common.game.logs.ActionsQueue;
import soc.common.game.logs.ActionsQueueImpl;
import soc.common.game.logs.ChatLog;
import soc.common.game.logs.ChatLogImpl;
import soc.common.game.logs.GameLog;
import soc.common.game.logs.GameLogImpl;
import soc.common.game.phases.GamePhase;
import soc.common.game.phases.LobbyGamePhase;
import soc.common.game.phases.PlayTurnsGamePhase;
import soc.common.game.phases.turnPhase.TurnPhase;
import soc.common.game.phases.turnPhase.TurnPhaseChangedEvent;
import soc.common.game.phases.turnPhase.TurnPhaseChangedHandler;
import soc.common.game.player.GamePlayer;
import soc.common.game.player.GamePlayerList;
import soc.common.game.settings.GameSettings;
import soc.common.game.statuses.GameStatus;
import soc.common.game.statuses.Playing;
import soc.common.game.trading.TradeResponse;
import soc.common.game.variants.GameRules;
import soc.common.game.variants.GameRulesImpl;
import soc.common.server.entities.UserList;
import soc.common.server.randomization.ClientRandom;
import soc.common.server.randomization.Random;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;

/*
 * Model of the game during a game
 */
public class Game implements Serializable
{
    private static final long serialVersionUID = -4310619750495087333L;
    // Event subscribers/broadcasting mechanism
    private transient EventBus eventBus = new SimpleEventBus();
    private transient Random random = new ClientRandom();

    // Logs
    private GameLog gameLog = new GameLogImpl();
    private ChatLog chatLog = new ChatLogImpl();

    // Abstracted rules
    private GameRules gameRules;

    // Players/bots/spectators
    private GamePlayerList players = new GamePlayerList();
    private UserList spectators = new UserList();

    // Game rules specific stuff
    private GameSettings gameSettings = new GameSettings();
    private Board board;
    private ResourceList bank = new ResourceList();
    private DevelopmentCardList developmentCardStack = new DevelopmentCardList();
    private Pirate pirate = null;
    private Robber robber = null;
    private Army largestArmy;
    private LongestRoad longestRoad = new LongestRoad();
    private Dice currentDice;
    private GamePlayer gameStarter;

    // State
    private ActionsQueue actionsQueue = new ActionsQueueImpl();
    private GamePhase currentPhase = new LobbyGamePhase();
    private Turn currentTurn;
    private GameStatus currentStatus = new Playing();

    // Misc properties
    private String name = "New Game";
    private int host;
    private int id;
    private Date startedDateTime;

    public Game()
    {
        gameRules = new GameRulesImpl(this);
    }

    /*
     * Returns a list of all PlayerPieces in the game residing on a HexPoint
     */
    public PointPieceList getAllPointPieces()
    {
        PointPieceList result = new PointPieceList();

        for (GamePlayer player : players)
            result.addList(player.getPointPieces());

        return result;
    }

    /*
     * Advances the TurnPhase in the PlayTurnsPhase to the next
     */
    public boolean advanceTurnPhase()
    {
        // Grab the current PlayTurnsPhase
        PlayTurnsGamePhase playTurns = (PlayTurnsGamePhase) currentPhase;

        // Grab new TurnPhase and keep reference to the old TurnPhase
        TurnPhase oldPhase = playTurns.getTurnPhase();
        TurnPhase newPhase = playTurns.getTurnPhase().next();

        // If there's a new TurnPhase, update local reference and notify
        // observers
        if (!oldPhase.equals(newPhase))
        {
            playTurns.setTurnPhase(newPhase);
            currentTurn.setTurnPhase(newPhase);
            eventBus.fireEvent(new TurnPhaseChangedEvent(newPhase, oldPhase));
            return true;
        } else
        {
            return false;
        }
    }

    /*
     * Advances the turn to the next turn
     */
    public void advanceTurn()
    {
        // Create a new turn
        Turn newTurn = currentPhase.nextTurn(this);

        // Update convenience method at the player
        currentTurn.getPlayer().setOnTurn(false);
        newTurn.getPlayer().setOnTurn(true);

        // keep reference to old turn and switch to the new turn
        Turn oldTurn = currentTurn;
        this.currentTurn = newTurn;

        // Notify handlers the current turn is changed
        eventBus.fireEvent(new TurnChangedEvent(oldTurn, newTurn));
    }

    public void addTradeResponse(TradeResponse response)
    {
        // Add the response to the current TradeOffer
        currentTurn.getTradeOffers().getLatestOffer().getResponses()
                        .addResponse(response);

        // Update whether or not we have all responses we expect to get
        if (currentTurn.getTradeOffers().getLatestOffer().getResponses().size() == players
                        .size() - 1)
            currentTurn.getTradeOffers().getLatestOffer()
                            .setResponsesCompleted(true);
    }

    /*
     * Adds given PlayerPiece to given player, and updates longest road if
     * necessary
     */
    public void addPiece(PlayerPiece piece, GamePlayer player)
    {
        piece.addToPlayer(player);

        if (piece instanceof BoardPiece)
            ((BoardPiece) piece).addToBoard(board);

        if (piece.affectsRoad())
            calculateLongestRoad();
    }

    /*
     * Initializes this game instance
     */
    public void start()
    {
        gameRules.setRules(this);
        board.layoutBoard(this);
    }

    /*
     * Returns a list of all PlayerPieces in the game residing on a HexSide
     */
    public SidePieceList getAllSidePieces()
    {
        SidePieceList result = new SidePieceList();

        for (GamePlayer player : players)
            result.addList(player.getSidePieces());

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

    /*
     * Returns true when this Game instance and given GameAction are in a valid
     * state to perform given GameAction
     */
    public boolean isValid(GameAction action)
    {
        // Bugger out when action itself isn't valid
        if (!action.isValid(this))
            return false;

        // When mustExpected flag is set, we expect the given GameAction to be
        // expected on the GameQueue
        if (action.mustExpected())
            if (actionsQueue.size() > 0)
            {
                GameAction expectedAction = actionsQueue.findExpected(action);
                if (expectedAction == null)
                    return false;
                else
                    return expectedAction.getClass() == action.getClass();
            } else
            {
                // Nothing present on the GameQueue. Bugger out.
                return false;
            }

        return true;
    }

    /*
     * Calculates longest road and updates LongestRoad if necessary TODO:
     * implement oldInvalid
     */
    public void calculateLongestRoad()
    {
        boolean oldInvalid = false;

        if (longestRoad.getRoute() != null
                        && !longestRoad.getRoute().validate())
            oldInvalid = true;

        // Call actual longest road calculation
        Route possibleNewLongest = board.getGraph().getLongestRoute(this,
                        longestRoad.getRoute());

        if (possibleNewLongest != null)
        {
            // Set a new route to the LongestRoad when possible new longest road
            // is
            // longer, has a different owner or the current longest road's owner
            // is
            // null
            if (longestRoad.getRoute() == null
                            || possibleNewLongest.getLength() != longestRoad
                                            .getRoute().getLength()
                            || possibleNewLongest.getPlayer().equals(
                                            longestRoad.getPlayer()))
            {
                // Remove it from the old player if old player exists
                if (longestRoad.getPlayer() != null)
                    longestRoad.removeFromPlayer(longestRoad.getPlayer());

                // Add it to new player if new player exists
                if (possibleNewLongest.getPlayer() != null)
                    longestRoad.addToPlayer(possibleNewLongest.getPlayer());

                // Update the longest road instance
                longestRoad.setRoute(possibleNewLongest);
            }
        }
    }

    /*
     * Returns a GamePlayer by given UserID. If no GamePlayer is found, returns
     * null
     */
    public GamePlayer getPlayerByID(int id)
    {
        for (GamePlayer p : players)
            if (p.getUser().getId() == id)
                return p;

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
            // If a PointPiece has given HexLocation contained, add the player
            // to the list of players having a town or city at given HexLocation
            for (HasPoint pointPiece : player.getPointPieces())
                if (pointPiece.getPoint().hasLocation(hexLocation))
                    playersAtHex.add(player);

        return playersAtHex;
    }

    /*
     * Advances GamePhase to the next phase
     */
    public void advanceGamePhase()
    {
        // Grab the new phase
        GamePhase newGamePhase = currentPhase.next(this);
        GamePhaseChangedEvent event = new GamePhaseChangedEvent(currentPhase,
                        newGamePhase);

        currentPhase = newGamePhase;

        // Fire a TurnPhaseChangedEvent when setting new phase to
        // PlayTurnsGamePhase
        if (currentPhase.isPlayTurns())
        {
            eventBus.fireEvent(new TurnPhaseChangedEvent(
                            ((PlayTurnsGamePhase) currentPhase).getTurnPhase(),
                            null));
        }

        // Start the next phase
        currentPhase.start(this);

        eventBus.fireEvent(event);
    }

    /*
     * Performs given GameAction
     */
    public void performAction(GameAction action)
    {
        // When given GameAction is expected, grab it from the queue and dequeue
        // the action
        GameAction expectedAction = actionsQueue.findExpected(action);

        if (expectedAction != null)
            actionsQueue.dequeueExpected(expectedAction);

        // Hand control of the actual performing to the GamePhase
        currentPhase.performAction(action, this);
    }

    public void initialize()
    {
        // Set first player on turn
        currentTurn = new TurnImpl(players.get(0), 0, null);

        moveRobberToDesert();

        currentStatus = new Playing();
        board.initialize();
    }

    /*
     * Move robber to desert hex, if available
     */
    private void moveRobberToDesert()
    {
        // Find DesertHex
        Hex desertHex = null;
        for (Hex hex : board.getHexes())
            if (hex instanceof DesertHex)
                desertHex = hex;

        // Move robber when a DesertHex is found
        if (desertHex != null)
            robber.setLocation(desertHex.getLocation());
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
            } else
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

    /*
     * Returns true if bots are present in this game
     */
    public boolean hasBots()
    {
        for (GamePlayer player : players)
            if (player.getBot() != null)
                return true;

        return false;
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

    public HandlerRegistration addTurnChangedEventHandler(
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

    /** @return the id */
    public int getId()
    {
        return id;
    }

    /** @param id
     *            the id to set */
    public Game setId(int id)
    {
        this.id = id;
        return this;
    }

    /** @return the startedDateTime */
    public Date getStartedDateTime()
    {
        return startedDateTime;
    }

    /** @param startedDateTime
     *            the startedDateTime to set */
    public Game setStartedDateTime(Date startedDateTime)
    {
        this.startedDateTime = startedDateTime;
        return this;
    }

    /** @return the name */
    public String getName()
    {
        return name;
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
        return longestRoad;
    }

    public Game setLongestRoute(LongestRoad longestRoute)
    {
        this.longestRoad = longestRoute;

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

    public UserList getSpectators()
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

    public Random getRandom()
    {
        return random;
    }
}
