package soc.common.game.trading;

import soc.common.game.Player;

public interface ITradeResponse
{
    public Player getPlayer();
    public ITradeOffer getOriginatingOffer();
}
