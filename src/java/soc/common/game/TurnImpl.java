package soc.common.game;

import soc.common.game.gamePhase.turnPhase.TurnPhase;
import soc.common.game.player.GamePlayer;
import soc.common.game.trading.TradeOfferList;

public class TurnImpl implements Turn
{
    private GamePlayer player;
    private int id;
    private TradeOfferList tradeOffers;

    /**
     * @param player
     *            the player to set
     */
    public Turn setPlayer(GamePlayer player)
    {
        this.player = player;

        return this;
    }

    @Override
    public TradeOfferList getTradeOffers()
    {
        return tradeOffers;
    }

    /**
     * @return the player
     */
    public GamePlayer getPlayer()
    {
        return player;
    }

    @Override
    public int getID()
    {
        return id;
    }

    @Override
    public Turn setID(int id)
    {
        this.id = id;
        return this;
    }

    @Override
    public TurnPhase getTurnPhase()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
