package org.soc.common.game.trading;

import org.soc.common.game.GamePlayer;

public interface TradeResponse {
  public TradeOffer getOriginatingOffer();
  public TradeResponse setOriginatingOffer(TradeOffer tradeOffer);
  public GamePlayer player();
  public void setTradeResources(TradePlayer tradePlayer);
  public boolean isCounterOffer();
  public boolean isRejection();
  public boolean isAccepted();
}
