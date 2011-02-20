package soc.common.game.trading;

import soc.common.actions.gameAction.trading.TradeOffer;
import soc.common.actions.gameAction.trading.TradePlayer;
import soc.common.game.player.GamePlayer;

public interface TradeResponse
{
    public TradeOffer getOriginatingOffer();

    public TradeResponse setOriginatingOffer(TradeOffer tradeOffer);

    public GamePlayer getPlayer();

    public void setTradeResources(TradePlayer tradePlayer);
}
