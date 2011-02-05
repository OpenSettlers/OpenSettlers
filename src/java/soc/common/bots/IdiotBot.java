package soc.common.bots;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.turnActions.standard.AcceptTradeOffer;
import soc.common.actions.gameAction.turnActions.standard.CounterTradeOffer;
import soc.common.actions.gameAction.turnActions.standard.LooseCards;
import soc.common.actions.gameAction.turnActions.standard.PlaceRobber;
import soc.common.actions.gameAction.turnActions.standard.RejectTradeOffer;
import soc.common.actions.gameAction.turnActions.standard.RobPlayer;
import soc.common.actions.gameAction.turnActions.standard.RollDice;
import soc.common.actions.gameAction.turnActions.standard.TradeOffer;
import soc.common.board.HexPoint;
import soc.common.board.HexSide;
import soc.common.board.resources.ResourceList;
import soc.common.board.routing.GraphPoint;
import soc.common.board.routing.GraphSide;
import soc.common.game.Game;
import soc.common.game.player.GamePlayer;
import soc.common.game.trading.TradeResponse;
import soc.common.server.random.Random;

/*
 * Simple bot implementation using randomness as main advice.
 */
public class IdiotBot implements Bot
{
    private Game game;
    private GamePlayer player;
    private Random random;

    public IdiotBot(Game game, GamePlayer player, Random random)
    {
        super();
        this.game = game;
        this.player = player;
        this.random = random;
    }

    @Override
    public void handTurn(BotPrincipal principal)
    {
        // We start always by rolling the dice
        RollDice rollDice = (RollDice) new RollDice().setPlayer(player);
        principal.performAction(rollDice);

        // 
    }

    @Override
    public HexSide pickFirstRoad()
    {
        return grabRandomHexSide(game.getBoard().getGraph()
                .getRoadCandidatesFirstTown(player));
    }

    /*
     * Queries set of possible towns and returns a random town location
     * 
     * @see soc.common.bots.Bot#pickFirstTown()
     */
    @Override
    public HexPoint pickFirstTown()
    {
        return grabRandomHexPoint(game.getBoard().getGraph()
                .getTownCandidatesFirstTown(player));
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
    public HexSide pickSecondRoad()
    {
        return grabRandomHexSide(game.getBoard().getGraph()
                .getRoadCandidatesSecondTown(player));
    }

    @Override
    public HexPoint pickSecondTown()
    {
        return grabRandomHexPoint(game.getBoard().getGraph()
                .getTownCandidatesFirstTown(player));
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
        size = size == 0 ? 0 : size++;
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public RobPlayer robPlayer()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
