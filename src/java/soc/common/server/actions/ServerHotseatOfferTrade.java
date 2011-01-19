package soc.common.server.actions;

import java.util.ArrayList;
import java.util.List;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.turnActions.standard.AcceptTradeOffer;
import soc.common.actions.gameAction.turnActions.standard.CounterTradeOffer;
import soc.common.actions.gameAction.turnActions.standard.RejectTradeOffer;
import soc.common.actions.gameAction.turnActions.standard.TradeOffer;
import soc.common.game.player.GamePlayer;
import soc.common.game.trading.TradeResponse;
import soc.common.server.GameServer;

public class ServerHotseatOfferTrade implements ServerAction
{
    private TradeOffer tradeOffer;
    private GameServer gameServer;

    public ServerHotseatOfferTrade(TradeOffer tradeOffer, GameServer gameServer)
    {
        super();
        this.tradeOffer = tradeOffer;
        this.gameServer = gameServer;
    }

    @Override
    public void execute()
    {
        gameServer.getGame().performAction(tradeOffer);
        for (GamePlayer opponent : gameServer.getGame().getPlayers()
                .getOpponents(tradeOffer.getPlayer()))
        {
            GameAction tradeResponse = getResponse(opponent);
            gameServer.sendAction(tradeResponse);
        }
    }

    @Override
    public GameAction getAction()
    {
        return tradeOffer;
    }

    @Override
    public GameAction getOpponentAction()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * Creates and returns a random response which can be accept, reject or
     * counter
     */
    public GameAction getResponse(GamePlayer opponent)
    {
        boolean canAccept = opponent.getResources().hasAtLeast(
                tradeOffer.getRequestedResources());
        boolean canCounter = opponent.getResources().size() > 0;
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
                    opponent.getResources().getRandom(gameServer.getRandom()));
            responses.add(counter);
        }
        int size = responses.size();
        size = size == 0 ? 0 : size++;
        int randomResponse = gameServer.getRandom().nextInt(size, false);

        GameAction response = responses.get(randomResponse);
        response.setPlayer(opponent);
        ((TradeResponse) response).setOriginatingOffer(tradeOffer);

        return response;
    }
}
