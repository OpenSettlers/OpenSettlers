package soc.common.game;

import java.io.Serializable;

import soc.common.game.gamePhase.turnPhase.TurnPhase;
import soc.common.game.player.GamePlayer;
import soc.common.game.trading.TradeOfferList;

public interface Turn extends Serializable
{
    public TradeOfferList getTradeOffers();

    public GamePlayer getPlayer();

    public int getID();

    public Turn setID(int id);

    public TurnPhase getTurnPhase();

    /**
     * @param player
     *            the player to set
     */
    public Turn setPlayer(GamePlayer player);
}