package soc.common.game;

import soc.common.board.pieces.abstractPieces.PlayerPiece;

public class StockItem
{
    private PlayerPiece piece;
    private int amount;
    /**
     * @return the piece
     */
    public PlayerPiece getPiece()
    {
        return piece;
    }
    /**
     * @param piece the piece to set
     */
    public StockItem setPiece(PlayerPiece piece)
    {
        this.piece = piece;
    
        // Enables fluent interface usage
        // http://en.wikipedia.org/wiki/Fluent_interface
        return this;
    }
    /**
     * @return the amount
     */
    public int getAmount()
    {
        return amount;
    }
    /**
     * @param amount the amount to set
     */
    public StockItem setAmount(int amount)
    {
        this.amount = amount;
    
        // Enables fluent interface usage
        // http://en.wikipedia.org/wiki/Fluent_interface
        return this;
    }
}
