package org.soc.common.game;

import java.io.Serializable;
import java.util.Date;

import org.soc.common.game.ActionsQueue.ActionsQueueImpl;
import org.soc.common.game.ChatLog.ChatLogImpl;
import org.soc.common.game.DiceChangedEvent.DiceChangedHandler;
import org.soc.common.game.DiceChangedEvent.HasDiceChangedHandlers;
import org.soc.common.game.GameLog.GameLogImpl;
import org.soc.common.game.GamePhase.LobbyGamePhase;
import org.soc.common.game.GamePhase.PlayTurnsGamePhase;
import org.soc.common.game.GamePhaseChangedEvent.GamePhaseChangedHandler;
import org.soc.common.game.GamePhaseChangedEvent.HasGamePhaseChangedHandlers;
import org.soc.common.game.GameRules.GameRulesImpl;
import org.soc.common.game.GameStatus.Playing;
import org.soc.common.game.Random.ClientRandom;
import org.soc.common.game.StatusChangedEvent.HasStatusChangedHandlers;
import org.soc.common.game.StatusChangedEvent.StatusChangedHandler;
import org.soc.common.game.Turn.TurnImpl;
import org.soc.common.game.TurnChangedEvent.HasTurnChangedHandlers;
import org.soc.common.game.TurnChangedEvent.TurnChangedHandler;
import org.soc.common.game.TurnPhaseChangedEvent.HasTurnPhaseChangedHandlers;
import org.soc.common.game.TurnPhaseChangedEvent.TurnPhaseChangedHandler;
import org.soc.common.game.actions.Action;
import org.soc.common.game.actions.GameAction;
import org.soc.common.game.board.Board;
import org.soc.common.game.board.HasPoint;
import org.soc.common.game.board.HexLocation;
import org.soc.common.game.board.Route;
import org.soc.common.game.hexes.DesertHex;
import org.soc.common.game.hexes.Hex;
import org.soc.common.game.pieces.Army;
import org.soc.common.game.pieces.LongestRoad;
import org.soc.common.game.pieces.Piece.BoardPiece;
import org.soc.common.game.pieces.Piece.PlayerPiece;
import org.soc.common.game.pieces.Pirate;
import org.soc.common.game.pieces.PointPieceList;
import org.soc.common.game.pieces.Robber;
import org.soc.common.game.pieces.SidePieceList;
import org.soc.common.game.trading.TradeOffer;
import org.soc.common.game.trading.TradeResponse;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import com.gwtplatform.dispatch.annotation.GenEvent;
import com.gwtplatform.dispatch.annotation.Order;

public class Game implements Serializable, HasDiceChangedHandlers, HasGamePhaseChangedHandlers,
        HasTurnChangedHandlers, HasTurnPhaseChangedHandlers,
        HasStatusChangedHandlers {
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
  private Dice dice;
  private GamePlayer gameStarter;
  // State
  private ActionsQueue actionsQueue = new ActionsQueueImpl();
  private GamePhase phase = new LobbyGamePhase();
  private Turn currentTurn;
  private GameStatus currentStatus = new Playing();
  // Misc properties
  private String name = "New Game";
  private int id;
  private Date startedDateTime;

  public Game() {
    gameRules = new GameRulesImpl(this);
  }
  /** Returns a list of all PlayerPieces in the game residing on a HexPoint */
  public PointPieceList allPointPieces() {
    PointPieceList result = new PointPieceList();
    for (GamePlayer player : players)
      result.addList(player.pointPieces());
    return result;
  }
  /** Advances the TurnPhase in the PlayTurnsPhase to the next */
  public boolean advanceTurnPhase() {
    // Grab the current PlayTurnsPhase
    PlayTurnsGamePhase playTurns = (PlayTurnsGamePhase) phase;
    // Grab new TurnPhase and keep reference to the old TurnPhase
    TurnPhase oldPhase = playTurns.turnPhase();
    TurnPhase newPhase = playTurns.turnPhase().next();
    // If there's a new TurnPhase, update local reference and notify
    // observers
    if (!oldPhase.equals(newPhase)) {
      playTurns.setTurnPhase(newPhase);
      currentTurn.setTurnPhase(newPhase);
      eventBus.fireEvent(new TurnPhaseChangedEvent(newPhase, oldPhase));
      return true;
    } else {
      return false;
    }
  }
  /** Advances the turn to the next turn */
  public void advanceTurn() {
    Turn newTurn = phase.nextTurn(this);
    currentTurn.player().setOnTurn(false);
    newTurn.player().setOnTurn(true);
    Turn oldTurn = currentTurn;
    this.currentTurn = newTurn;
    eventBus.fireEvent(new TurnChangedEvent(oldTurn, newTurn));
  }
  /** Adds given trade response to the latest offers' responses */
  public void addTradeResponse(TradeResponse response) {
    TradeOffer latestOffer = currentTurn.getTradeOffers().getLatestOffer();
    // Add the response to the current TradeOffer
    latestOffer.getResponses().addResponse(response);
    // Update whether or not we have all responses we expect to get
    if (latestOffer.getResponses().size() == players.size() - 1)
      latestOffer.setResponsesCompleted(true);
  }
  /** Adds given PlayerPiece to given player, and updates longest road if necessary */
  public void addPiece(PlayerPiece piece, GamePlayer player) {
    piece.addTo(player);
    if (piece instanceof BoardPiece)
      ((BoardPiece) piece).addToBoard(board);
    if (piece.affectsRoad())
      calculateLongestRoad();
  }
  /** Initializes this game instance */
  public void start() {
    gameRules.setRules(this);
    board.layoutBoard(this);
  }
  /** Returns a list of all PlayerPieces in the game residing on a HexSide */
  public SidePieceList allSidePieces() {
    SidePieceList result = new SidePieceList();
    for (GamePlayer player : players)
      result.addList(player.sidePieces());
    return result;
  }
  public void updateStatus() {
    GameStatus newStatus = null;
    if (actionsQueue.isWaitingForActions()) {
      // newStatus = new WaitingForTurnActions()
      // .setBlockingActions(actionsQueue.getBlockingActions());
    }
    currentStatus = newStatus;
  }
  /** Returns true when given action is allowed to be performed */
  public boolean isAllowed(Action action) {
    if (action instanceof GameAction) {
      GameAction gameAction = (GameAction) action;
      // Check if the status allows the action
      if (currentStatus.isGameBlocking()) {
        return false;
      }
      // Check if the current phase allows the action
      if (!phase.isAllowed(gameAction)) {
        return false;
      }
    }
    return true;
  }
  /** Returns true when this Game instance and given GameAction are in a valid state to perform given
   * GameAction */
  public boolean isValid(GameAction action) {
    // Bugger out when action itself isn't valid
    if (!action.isValid(this))
      return false;
    // When mustExpected flag is set, we expect the given GameAction to be
    // expected on the GameQueue
    if (action.mustExpected())
      if (actionsQueue.size() > 0) {
        GameAction expectedAction = actionsQueue.findExpected(action);
        if (expectedAction == null)
          return false;
        else
          return expectedAction.getClass() == action.getClass();
      } else {
        // Nothing present on the GameQueue. Bugger out.
        return false;
      }
    return true;
  }
  /** Calculates longest road and updates LongestRoad if necessary */
  // TODO: implement oldInvalid 
  public void calculateLongestRoad() {
    boolean oldInvalid = false;
    if (longestRoad.route() != null && !longestRoad.route().validate())
      oldInvalid = true;
    // Call actual longest road calculation
    Route possibleNewLongest = board.graph().getLongestRoute(this, longestRoad.route());
    if (possibleNewLongest != null)
      // Set a new route to the LongestRoad when possible new longest road is
      // longer, has a different owner or the current longest road's owner is null
      if (longestRoad.route() == null
              || possibleNewLongest.length() != longestRoad.route().length()
              || possibleNewLongest.player().equals(longestRoad.player())) {
        // Remove it from the old player if old player exists
        if (longestRoad.player() != null)
          longestRoad.removeFrom(longestRoad.player());
        // Add it to new player if new player exists
        if (possibleNewLongest.player() != null)
          longestRoad.addTo(possibleNewLongest.player());
        // Update the longest road instance
        longestRoad.setRoute(possibleNewLongest);
      }
  }
  /** Returns a GamePlayer by given UserID. If no GamePlayer is found, returns null */
  public GamePlayer playerById(int id) {
    for (GamePlayer player : players)
      if (player.user().id() == id)
        return player;
    return null;
  }
  /** Returns a list of GamePlayers having a town or city at given HexLocation */
  public GamePlayerList playersAtHex(HexLocation hexLocation, GamePlayer skipPlayer) {
    GamePlayerList playersAtHex = new GamePlayerList();
    // Check all opponents
    for (GamePlayer player : players.opponents(skipPlayer))
      // If a PointPiece has given HexLocation contained, add the player
      // to the list of players having a town or city at given HexLocation
      for (HasPoint pointPiece : player.pointPieces())
        if (pointPiece.point().hasLocation(hexLocation))
          playersAtHex.add(player);
    return playersAtHex;
  }
  /** Advances GamePhase to the next phase */
  public void advanceGamePhase() {
    // Grab the new phase
    GamePhase newGamePhase = phase.next(this);
    GamePhaseChangedEvent gamePhaseChanged = new GamePhaseChangedEvent(phase, newGamePhase);
    phase = newGamePhase;
    // Fire a TurnPhaseChangedEvent when setting new phase to
    // PlayTurnsGamePhase
    if (phase.isPlayTurns()) {
      eventBus.fireEvent(new TurnPhaseChangedEvent(
              ((PlayTurnsGamePhase) phase).turnPhase(), null));
    }
    // Start the next phase
    phase.start(this);
    eventBus.fireEvent(gamePhaseChanged);
  }
  /** Performs given GameAction */
  public void performAction(GameAction action) {
    // When given GameAction is expected, grab it from the queue and dequeue
    // the action
    GameAction expectedAction = actionsQueue.findExpected(action);
    if (expectedAction != null)
      actionsQueue.dequeueExpected(expectedAction);
    // Hand control of the actual performing to the GamePhase
    phase.performAction(action, this);
  }
  /** Initializes the game such that a host can start it */
  public void initialize() {
    // Set first player on turn
    currentTurn = new TurnImpl(players.get(0), 0, null);
    moveRobberToDesert();
    currentStatus = new Playing();
    board.initialize();
  }
  /** Move robber to desert hex, if available */
  private void moveRobberToDesert() {
    Hex desertHex = findDesertHex();
    if (desertHex != null)
      robber.setLocation(desertHex.location());
  }
  /** Returns first occurrence of a DesertHex or null if no DesertHex found */
  private Hex findDesertHex() {
    Hex desertHex = null;
    for (Hex hex : board.hexes())
      if (hex instanceof DesertHex)
        desertHex = hex;
    return desertHex;
  }
  /** Switches largest army to specified player */
  public void switchLargestArmyIfNeeded(GamePlayer player) {
    if (player.army().amountSoldiers() > 2) {
      GamePlayer opponent = largestArmy.player();
      if (opponent == null) {
        // No one owns LA yet, set it to given player
        player.army().addTo(player);
        largestArmy.setPlayer(player);
      } else {
        // There is an opponent with a bigger army
        if (!opponent.equals(player)
                && player.army().isBiggerThen(opponent.army())) {
          // Player wins LA from previous player
          opponent.army().removeFrom(opponent);
          player.army().addTo(player);
          largestArmy.setPlayer(player);
        }
      }
    }
  }
  /** Returns true if bots are present in this game */
  public boolean hasBots() {
    for (GamePlayer player : players)
      if (player.bot() != null)
        return true;
    return false;
  }

  @GenEvent public class StatusChanged {
    @Order(0) GameStatus newStatus;
    @Order(1) GameStatus oldStatus;
  }

  @GenEvent public class GamePhaseChanged {
    @Order(0) GamePhase previousPhase;
    @Order(1) GamePhase newPhase;
  }

  @GenEvent public class LongestRoadChanged {
    @Order(0) Route oldRoute;
    @Order(1) Route newRoute;
  }

  @GenEvent public class TurnPhaseChanged {
    @Order(0) TurnPhase newPhase;
    @Order(1) TurnPhase oldPhase;
  }

  @GenEvent public class TurnChanged {
    @Order(0) Turn oldTurn;
    @Order(1) Turn newTurn;
  }

  @GenEvent public class DiceChanged {
    @Order(0) Dice newDice;
  }

  public HandlerRegistration addTurnPhaseChangedHandler(TurnPhaseChangedHandler handler) {
    return eventBus.addHandler(TurnPhaseChangedEvent.getType(), handler);
  }
  @Override public HandlerRegistration addStatusChangedHandler(StatusChangedHandler handler) {
    return eventBus.addHandler(StatusChangedEvent.getType(), handler);
  }
  @Override public HandlerRegistration addTurnChangedHandler(TurnChangedHandler handler) {
    return eventBus.addHandler(TurnChangedEvent.getType(), handler);
  }
  @Override public HandlerRegistration addGamePhaseChangedHandler(GamePhaseChangedHandler handler) {
    return eventBus.addHandler(GamePhaseChangedEvent.getType(), handler);
  }
  @Override public HandlerRegistration addDiceChangedHandler(DiceChangedHandler handler) {
    return eventBus.addHandler(DiceChangedEvent.getType(), handler);
  }
  public Dice dice() {
    return dice;
  }
  public Game setDice(Dice newDice) {
    this.dice = newDice;
    eventBus.fireEvent(new DiceChangedEvent(newDice));
    return this;
  }
  public Board board() {
    return board;
  }
  public Game setBoard(Board board) {
    this.board = board;
    return this;
  }
  public int id() {
    return id;
  }
  public Game setId(int id) {
    this.id = id;
    return this;
  }
  public Date startedDateTime() {
    return startedDateTime;
  }
  public Game setStartedDateTime(Date startedDateTime) {
    this.startedDateTime = startedDateTime;
    return this;
  }
  public String name() {
    return name;
  }
  public GameStatus getStatus() {
    return currentStatus;
  }
  public void setStatus(GameStatus newStatus) {
    GameStatus oldStatus = currentStatus;
    currentStatus = newStatus;
    eventBus.fireEvent(new StatusChangedEvent(newStatus, oldStatus));
  }
  public Turn turn() {
    return currentTurn;
  }
  public GameRules rules() {
    return gameRules;
  }
  public ChatLog getChatLog() {
    return chatLog;
  }
  public Robber robber() {
    return robber;
  }
  public Game setRobber(Robber robber) {
    this.robber = robber;
    return this;
  }
  public DevelopmentCardList developmentCardStack() {
    return developmentCardStack;
  }
  public Game setDevelopmentCards(DevelopmentCardList developmentCards) {
    this.developmentCardStack = developmentCards;
    return this;
  }
  public GamePlayer getStartPlayer() {
    return gameStarter;
  }
  public Game setStartPlayer(GamePlayer gameStarter) {
    this.gameStarter = gameStarter;
    players.setStartPlayer(gameStarter);
    return this;
  }
  public Game setDevelopmentCardStack(DevelopmentCardList developmentCardStack) {
    this.developmentCardStack = developmentCardStack;
    return this;
  }
  public Army largestArmy() {
    return largestArmy;
  }
  public Game setLargestArmy(Army largestArmy) {
    this.largestArmy = largestArmy;
    return this;
  }
  public LongestRoad longestRoute() {
    return longestRoad;
  }
  public Game setLongestRoute(LongestRoad longestRoute) {
    this.longestRoad = longestRoute;
    return this;
  }
  public GameSettings gameSettings() {
    return gameSettings;
  }
  public void setGameSettings(GameSettings gameSettings) {
    this.gameSettings = gameSettings;
  }
  public ResourceList bank() {
    return bank;
  }
  public void setBank(ResourceList bank) {
    this.bank = bank;
  }
  public ActionsQueue actionsQueue() {
    return actionsQueue;
  }
  public void setActionsQueue(ActionsQueue actionsQueue) {
    this.actionsQueue = actionsQueue;
  }
  public GamePlayerList players() {
    return players;
  }
  public UserList getSpectators() {
    return spectators;
  }
  public GameLog log() {
    return gameLog;
  }
  public void setGameLog(GameLogImpl gameLog) {
    this.gameLog = gameLog;
  }
  public Pirate getPirate() {
    return pirate;
  }
  public void setPirate(Pirate pirate) {
    this.pirate = pirate;
  }
  public GamePhase phase() {
    return phase;
  }
  public Random random() {
    return random;
  }
  @Override public void fireEvent(GwtEvent<?> event) {
    eventBus.fireEvent(event);
  }
}
