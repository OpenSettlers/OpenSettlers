package soc.gwtClient.game.widgetsInterface.main;

import soc.common.board.pieces.abstractPieces.PlayerPiece;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.behaviour.gamePanel.TradeFirst;

public interface BankTradeWidget
{
    public void show();

    public void hide();

    public void setPopupPosition(int x, int y);

    /**
     * @param pieceToTradeFor
     *            the pieceToTradeFor to set
     */
    public void setPieceToTradeFor(PlayerPiece pieceToTradeFor,
            TradeFirst tradeFirst);

    public void setDevcardTrade(TradeFirst tradeFirst);

    /**
     * @param player
     *            the player to set
     */
    public BankTradeWidget setPlayer(GamePlayer player);

    /**
     * @return the player
     */
    public GamePlayer getPlayer();
}
