package soc.common.game.trading;

import soc.common.actions.gameAction.turnActions.standard.TradeOffer;
import soc.common.game.player.GamePlayer;

public interface TradeResponse
{
    public TradeOffer getOriginatingOffer();

    public TradeResponse setOriginatingOffer(TradeOffer tradeOffer);

    public GamePlayer getPlayer();
}
