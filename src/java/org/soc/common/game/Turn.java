package org.soc.common.game;

import java.io.Serializable;

import org.soc.common.game.phases.turnPhase.TurnPhase;
import org.soc.common.game.player.GamePlayer;
import org.soc.common.game.trading.TradeOfferList;


public interface Turn extends Serializable
{
    public TradeOfferList getTradeOffers();

    public GamePlayer getPlayer();

    public int getID();

    public Turn setID(int id);

    public TurnPhase getTurnPhase();

    public void setTurnPhase(TurnPhase phase);

    /**
     * @param player
     *            the player to set
     */
    public Turn setPlayer(GamePlayer player);
}