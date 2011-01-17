package soc.common.server.actions;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.turnActions.standard.AcceptTradeOffer;
import soc.common.actions.gameAction.turnActions.standard.CounterTradeOffer;
import soc.common.actions.gameAction.turnActions.standard.RejectTradeOffer;
import soc.common.actions.gameAction.turnActions.standard.TradeOffer;
import soc.common.game.player.GamePlayer;
import soc.common.server.GameServer;

public class ServerOfferTrade implements ServerAction
{
    private TradeOffer tradeOffer;
    private GameServer gameServer;

    public ServerOfferTrade(TradeOffer tradeOffer, GameServer gameServer)
    {
        super();
        this.tradeOffer = tradeOffer;
        this.gameServer = gameServer;
    }

    @Override
    public void execute()
    {
        for (GamePlayer opponent : gameServer.getGame().getPlayers()
                .getOpponents(tradeOffer.getPlayer()))
        {

        }
    }

    @Override
    public GameAction getAction()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GameAction getOpponentAction()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public GameAction getResponse(GamePlayer opponent)
    {
        GameAction response = null;

        boolean canCounterOffer = false;
        if (opponent.getResources().size() > 0)
            canCounterOffer = true;

        int randomNumber = gameServer.getRandom().nextInt(2, canCounterOffer);
        switch (randomNumber)
        {
        case 0:
            CounterTradeOffer counter = new CounterTradeOffer();
            counter.getOfferedResources().add(
                    tradeOffer.getRequestedResources());
            counter.getRequestedResources().add(
                    opponent.getResources().getRandom(gameServer.getRandom()));
        case 1:
            response = new RejectTradeOffer();
        case 2:
            response = new AcceptTradeOffer();
        }
        response.setPlayer(opponent);

        return response;
    }
}
