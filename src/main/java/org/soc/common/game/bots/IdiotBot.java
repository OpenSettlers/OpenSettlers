package org.soc.common.game.bots;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.soc.common.game.Game;
import org.soc.common.game.GamePlayer;
import org.soc.common.game.GamePlayerList;
import org.soc.common.game.Random;
import org.soc.common.game.Resource;
import org.soc.common.game.ResourceList;
import org.soc.common.game.TurnPhase;
import org.soc.common.game.Variant;
import org.soc.common.game.Variant.Standard;
import org.soc.common.game.actions.BuildRoad;
import org.soc.common.game.actions.BuildTown;
import org.soc.common.game.actions.CounterTradeOffer;
import org.soc.common.game.actions.EndTurn;
import org.soc.common.game.actions.GameAction;
import org.soc.common.game.actions.LooseCards;
import org.soc.common.game.actions.PlaceRobber;
import org.soc.common.game.actions.RobPlayer;
import org.soc.common.game.actions.RollDice;
import org.soc.common.game.board.GraphPoint;
import org.soc.common.game.board.GraphSide;
import org.soc.common.game.board.HexLocation;
import org.soc.common.game.board.HexPoint;
import org.soc.common.game.board.HexSide;
import org.soc.common.game.bots.Bot.AbstractBot;
import org.soc.common.game.bots.NameStrategy.IdioticNames;
import org.soc.common.game.trading.AcceptTradeOffer;
import org.soc.common.game.trading.RejectTradeOffer;
import org.soc.common.game.trading.TradeOffer;
import org.soc.common.game.trading.TradeResponse;

/** Simple bot implementation using randomness as main advice. */
public class IdiotBot extends AbstractBot
{
  private static final long serialVersionUID = 2413238692032425276L;
  private Game game;
  private GamePlayer player;
  private Random random;
  private static List<Class<? extends Variant>> supportedRuleSets = new ArrayList<Class<? extends Variant>>();
  private TurnPhase turnPhase;
  static {
    supportedRuleSets.add(Standard.class);
  }

  public IdiotBot(Game game, GamePlayer player, Random random) {
    super();
    this.game = game;
    this.player = player;
    this.random = random;
    botUser = new BotUser();
    botUser.setName(new IdioticNames(random).getName());
  }
  /** Does as little as possible in a turn */
  @Override public void handTurn(BotPrincipal principal) {
    if (game.turn().getTurnPhase().isBeforeDiceRoll())
      handleBeforeDiceRollTurnPhase(principal);
    if (game.turn().getTurnPhase().isTrading())
      handleTradingTurnPhase(principal);
    if (game.turn().getTurnPhase().isBuilding())
      handleBuildTurnPhase(principal);
  }
  /* Simply rolls the dice */
  private void handleBeforeDiceRollTurnPhase(BotPrincipal principal) {
    RollDice rollDice = (RollDice) new RollDice().setPlayer(player);
    principal.performAction(rollDice);
  }
  /* No trading supported */
  private void handleTradingTurnPhase(BotPrincipal principal) {
    // No trading yet
    handleBuildTurnPhase(principal);
  }
  private void handleBuildTurnPhase(BotPrincipal principal) {
    // This bot is really a know-no. It just ends its turn.
    EndTurn endTurn = new EndTurn();
    endTurn.setPlayer(player);
    principal.performAction(endTurn);
  }
  @Override public BuildRoad pickFirstRoad() {
    HexSide roadPick = grabRandomHexSide(game.board().graph()
            .getRoadCandidatesFirstTown(player));
    return (BuildRoad) new BuildRoad().setSideLocation(roadPick).setPlayer(
            player);
  }
  /** Queries set of possible towns and returns a random town location */
  @Override public BuildTown pickFirstTown() {
    HexPoint townPick = grabRandomHexPoint(game.board().graph()
            .getTownCandidatesFirstTown(player));
    return (BuildTown) new BuildTown().setPointLocation(townPick).setPlayer(player);
  }
  @Override public ResourceList pickGold(int amount) {
    ResourceList goldPick = new ResourceList();
    for (int i = 0; i < amount; i++) {
      int randomResource = random.nextInt(game.rules()
              .tradeableResources().size(), false);
      goldPick.add(game.rules().tradeableResources()
              .get(randomResource));
    }
    return goldPick;
  }
  @Override public BuildRoad pickSecondRoad() {
    HexSide roadPick = grabRandomHexSide(game.board().graph()
            .getRoadCandidatesSecondTown(player));
    return (BuildRoad) new BuildRoad().setSideLocation(roadPick).setPlayer(player);
  }
  @Override public BuildTown pickSecondTown() {
    HexPoint townPick = grabRandomHexPoint(game.board().graph()
            .getTownCandidatesFirstTown(player));
    return (BuildTown) new BuildTown().setPointLocation(townPick).setPlayer(player);
  }
  /** Creates a list of possible responses, then answers with random response from that list */
  @Override public GameAction respondToTrade(TradeOffer tradeOffer) {
    boolean canAccept = player.resources().hasAtLeast(
            tradeOffer.getRequestedResources());
    boolean canCounter = player.resources().size() > 0;
    // Keep track of possible responses
    List<GameAction> responses = new ArrayList<GameAction>();
    // Add the possible responses to the list
    // Reject is always possible
    responses.add(new RejectTradeOffer());
    // We have resources to accept?
    if (canAccept)
      responses.add(new AcceptTradeOffer());
    // Have alternative resources to counter offer?
    if (canCounter)
    {
      CounterTradeOffer counter = new CounterTradeOffer();
      counter.getOfferedResources().addList(
              tradeOffer.getRequestedResources());
      counter.getRequestedResources().add(
              player.resources().getRandom(random));
      responses.add(counter);
    }
    GameAction response = null;
    if (responses.size() == 1)
      response = responses.get(0);
    else
    {
      int randomResponse = random.nextInt(responses.size(), false);
      response = responses.get(randomResponse);
    }
    response.setPlayer(player);
    ((TradeResponse) response).setOriginatingOffer(tradeOffer);
    return response;
  }
  @Override public LooseCards looseCards(int amount) {
    LooseCards looseCards = new LooseCards();
    looseCards.setPlayer(player);
    ResourceList lostCards = new ResourceList();
    ResourceList copy = player.resources().copy();
    for (int i = 0; i < amount; i++)
    {
      int pickedCard = random.nextInt(copy.size(), false);
      Resource pickedResource = copy.get(pickedCard);
      copy.remove(pickedResource);
      lostCards.add(pickedResource);
    }
    looseCards.setLostCards(lostCards);
    return looseCards;
  }
  @Override public PlaceRobber placeRobber() {
    PlaceRobber placeRobber = new PlaceRobber();
    placeRobber.setPlayer(player);
    // Pick a random location
    List<HexLocation> possibleLocations = game.board().graph()
            .getPossibleRobberLocations();
    HexLocation randomLocation = possibleLocations.get(random.nextInt(
            possibleLocations.size(), false));
    placeRobber.setNewLocation(randomLocation);
    return placeRobber;
  }
  @Override public RobPlayer robPlayer() {
    RobPlayer robPlayer = new RobPlayer();
    robPlayer.setPlayer(player);
    GamePlayerList robCandidates = game.playersAtHex(game.robber()
            .location(), player);
    if (robCandidates.size() > 0)
    {
      GamePlayer robbedPlayer = robCandidates.get(random.nextInt(
              robCandidates.size(), false));
      robPlayer.setRobbedPlayer(robbedPlayer);
    }
    return robPlayer;
  }
  private HexPoint grabRandomHexPoint(Set<GraphPoint> choices) {
    int randomNumer = random.nextInt(choices.size(), false);
    int i = 0;
    for (GraphPoint graphPoint : choices)
    {
      if (i == randomNumer)
        return graphPoint.hexPoint();
      i++;
    }
    throw new AssertionError("Expected a town pick by now");
  }
  private HexSide grabRandomHexSide(Set<GraphSide> choices) {
    int randomNumer = random.nextInt(choices.size(), false);
    int i = 0;
    for (GraphSide graphSide : choices) {
      if (i == randomNumer)
        return graphSide.side();
      i++;
    }
    throw new AssertionError("Expected a road pick by now");
  }
  @Override public List<Class<? extends Variant>> getSupportedVariants() {
    return supportedRuleSets;
  }
  @Override public boolean isAnonymous() {
    return false;
  }
}
