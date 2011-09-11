package org.soc.common.views.widgetsInterface.dialogs;

import org.soc.common.game.GamePlayer;
import org.soc.common.game.actions.GameBehaviour.TradeFirst;
import org.soc.common.game.pieces.Piece.PlayerPiece;

public interface BankTradeWidget {
  public void show();
  public void hide();
  public void setPopupPosition(int x, int y);
  public void setPieceToTradeFor(PlayerPiece pieceToTradeFor, TradeFirst tradeFirst);
  public void setDevcardTrade(TradeFirst tradeFirst);
  public BankTradeWidget setPlayer(GamePlayer player);
  public GamePlayer getPlayer();
}
