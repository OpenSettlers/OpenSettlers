package org.soc.common.game.trading;

import org.soc.common.actions.gameAction.trading.TradeOffer;
import org.soc.common.actions.gameAction.trading.TradePlayer;
import org.soc.common.game.player.GamePlayer;

public interface TradeResponse
{
    public TradeOffer getOriginatingOffer();

    public TradeResponse setOriginatingOffer(TradeOffer tradeOffer);

    public GamePlayer getPlayer();

    public void setTradeResources(TradePlayer tradePlayer);

    public boolean isCounterOffer();

    public boolean isRejection();

    public boolean isAccepted();
}
