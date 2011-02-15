package soc.common.bots;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.turnActions.EndTurn;
import soc.common.actions.gameAction.turnActions.standard.AcceptTradeOffer;
import soc.common.actions.gameAction.turnActions.standard.BuildRoad;
import soc.common.actions.gameAction.turnActions.standard.BuildTown;
import soc.common.actions.gameAction.turnActions.standard.CounterTradeOffer;
import soc.common.actions.gameAction.turnActions.standard.LooseCards;
import soc.common.actions.gameAction.turnActions.standard.PlaceRobber;
import soc.common.actions.gameAction.turnActions.standard.RejectTradeOffer;
import soc.common.actions.gameAction.turnActions.standard.RobPlayer;
import soc.common.actions.gameAction.turnActions.standard.RollDice;
import soc.common.actions.gameAction.turnActions.standard.TradeOffer;
import soc.common.board.HexLocation;
import soc.common.board.HexPoint;
import soc.common.board.HexSide;
import soc.common.board.resources.ResourceList;
import soc.common.board.routing.GraphPoint;
import soc.common.board.routing.GraphSide;
import soc.common.bots.namingStrategies.IdioticNames;
import soc.common.game.Game;
import soc.common.game.player.GamePlayer;
import soc.common.game.player.GamePlayerList;
import soc.common.game.trading.TradeResponse;
import soc.common.game.variants.Standard;
import soc.common.game.variants.Variant;
import soc.common.server.entities.BotUser;
import soc.common.server.randomization.Random;

/*
 * Simple bot implementation using randomness as main advice.
 */
public class IdiotBot extends AbstractBot
{
    private static final long serialVersionUID = 2413238692032425276L;
    private Game game;
    private GamePlayer player;
    private Random random;
    private static List<Class<? extends Variant>> supportedRuleSets = new ArrayList<Class<? extends Variant>>();

    static
    {
        supportedRuleSets.add(Standard.class);
    }

    public IdiotBot(Game game, GamePlayer player, Random random)
    {
        super();
        this.game = game;
        this.player = player;
        this.random = random;

        botUser = new BotUser();
        botUser.setName(new IdioticNames(random).getName());
    }

    @Override
    public void handTurn(BotPrincipal principal)
    {
        // We start always by rolling the dice
        RollDice rollDice = (RollDice) new RollDice().setPlayer(player);
        principal.performAction(rollDice);

        // Hand control back to principal to perform possible robbing, robber
        // moving, card loosing & gold picking
        principal.handleActionsQueue();

        // This bot is really a know-no. It just ends its turn.
        EndTurn endTurn = new EndTurn();
        endTurn.setPlayer(player);
        principal.performAction(endTurn);
    }

    @Override
    public BuildRoad pickFirstRoad()
    {
        HexSide roadPick = grabRandomHexSide(game.getBoard().getGraph()
                .getRoadCandidatesFirstTown(player));
        return (BuildRoad) new BuildRoad().setSideLocation(roadPick).setPlayer(
                player);
    }

    /*
     * Queries set of possible towns and returns a random town location
     * 
     * @see soc.common.bots.Bot#pickFirstTown()
     */
    @Override
    public BuildTown pickFirstTown()
    {
        HexPoint townPick = grabRandomHexPoint(game.getBoard().getGraph()
                .getTownCandidatesFirstTown(player));
        return (BuildTown) new BuildTown().setPointLocation(townPick)
                .setPlayer(player);
    }

    @Override
    public ResourceList pickGold(int amount)
    {
        ResourceList goldPick = new ResourceList();

        for (int i = 0; i < amount; i++)
        {
            int randomResource = random.nextInt(game.getRules()
                    .getTradeableResources().size(), false);
            goldPick.add(game.getRules().getTradeableResources().get(
                    randomResource));
        }

        return goldPick;
    }

    @Override
    public BuildRoad pickSecondRoad()
    {
        HexSide roadPick = grabRandomHexSide(game.getBoard().getGraph()
                .getRoadCandidatesSecondTown(player));
        return (BuildRoad) new BuildRoad().setSideLocation(roadPick).setPlayer(
                player);
    }

    @Override
    public BuildTown pickSecondTown()
    {
        HexPoint townPick = grabRandomHexPoint(game.getBoard().getGraph()
                .getTownCandidatesFirstTown(player));
        return (BuildTown) new BuildTown().setPointLocation(townPick)
                .setPlayer(player);
    }

    @Override
    public GameAction respondToTrade(TradeOffer tradeOffer)
    {
        boolean canAccept = player.getResources().hasAtLeast(
                tradeOffer.getRequestedResources());
        boolean canCounter = player.getResources().size() > 0;
        List<GameAction> responses = new ArrayList<GameAction>();

        responses.add(new RejectTradeOffer());

        if (canAccept)
            responses.add(new AcceptTradeOffer());

        if (canCounter)
        {
            CounterTradeOffer counter = new CounterTradeOffer();
            counter.getOfferedResources().addList(
                    tradeOffer.getRequestedResources());
            counter.getRequestedResources().add(
                    player.getResources().getRandom(random));
            responses.add(counter);
        }
        int size = responses.size();
        size = size == 0 ? 0 : size + 1;
        int randomResponse = random.nextInt(size, false);

        GameAction response = responses.get(randomResponse);
        response.setPlayer(player);
        ((TradeResponse) response).setOriginatingOffer(tradeOffer);

        return response;
    }

    @Override
    public LooseCards looseCards(int amount)
    {
        LooseCards looseCards = new LooseCards();
        looseCards.setPlayer(player);
        ResourceList lostCards = new ResourceList();

        for (int i = 0; i < amount; i++)
            lostCards.add(player.getResources().get(i));

        looseCards.setLostCards(lostCards);

        return looseCards;
    }

    @Override
    public PlaceRobber placeRobber()
    {
        PlaceRobber placeRobber = new PlaceRobber();
        placeRobber.setPlayer(player);

        // Pick a random location
        List<HexLocation> possibleLocations = game.getBoard().getGraph()
                .getPossibleRobberLocations();
        HexLocation randomLocation = possibleLocations.get(random.nextInt(
                possibleLocations.size(), false));

        placeRobber.setNewLocation(randomLocation);

        return placeRobber;
    }

    @Override
    public RobPlayer robPlayer()
    {
        RobPlayer robPlayer = new RobPlayer();
        robPlayer.setPlayer(player);

        GamePlayerList robCandidates = game.getPlayersAtHex(game.getRobber()
                .getLocation(), player);

        if (robCandidates.size() > 0)
        {
            GamePlayer robbedPlayer = robCandidates.get(random.nextInt(
                    robCandidates.size(), false));
            robPlayer.setRobbedPlayer(robbedPlayer);
        }

        return robPlayer;
    }

    private HexPoint grabRandomHexPoint(Set<GraphPoint> choices)
    {
        int randomNumer = random.nextInt(choices.size(), false);
        int i = 0;
        for (GraphPoint graphPoint : choices)
        {
            if (i == randomNumer)
                return graphPoint.getPoint();

            i++;
        }

        throw new AssertionError("Expected a town pick by now");
    }

    private HexSide grabRandomHexSide(Set<GraphSide> choices)
    {
        int randomNumer = random.nextInt(choices.size(), false);
        int i = 0;
        for (GraphSide graphSide : choices)
        {
            if (i == randomNumer)
                return graphSide.getSide();

            i++;
        }

        throw new AssertionError("Expected a road pick by now");
    }

    @Override
    public List<Class<? extends Variant>> getSupportedVariants()
    {
        return supportedRuleSets;
    }

}
