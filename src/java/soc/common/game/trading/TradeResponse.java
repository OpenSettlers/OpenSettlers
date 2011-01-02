package soc.common.game.trading;

import soc.common.game.GamePlayer;

public interface TradeResponse
{
    public GamePlayer getPlayer();
    public TradeOffer getOriginatingOffer();
}
