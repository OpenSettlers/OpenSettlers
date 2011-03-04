package soc.common.game;

import soc.common.game.phases.turnPhase.TurnPhase;
import soc.common.game.player.GamePlayer;
import soc.common.game.trading.TradeOfferList;

public class TurnImpl implements Turn
{
    private static final long serialVersionUID = -7392096314044521868L;
    private GamePlayer player;
    private int id = 0;
    private TradeOfferList tradeOffers = new TradeOfferList();
    private TurnPhase turnPhase;

    public TurnImpl(GamePlayer player, int id, TurnPhase turnPhase)
    {
        super();
        this.player = player;
        this.id = id;
        this.turnPhase = turnPhase;
    }

    public TurnImpl()
    {
        // Empty default instantiable constructor
    }

    public TurnImpl(GamePlayer player)
    {
        super();
        this.player = player;
    }

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
        return turnPhase;
    }

    @Override
    public void setTurnPhase(TurnPhase phase)
    {
        this.turnPhase = phase;
    }

}
