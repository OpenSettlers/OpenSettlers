package soc.common.board.pieces;

import java.util.ArrayList;


public class PlayerPieceList extends ArrayList<PlayerPiece>
{
    /*
     * Returns a list of pieces of given type
     */
    public PlayerPieceList ofType(PlayerPiece type)
    {
        PlayerPieceList result = new PlayerPieceList();
        
        for (PlayerPiece piece : this)
        {
            if (piece.getClass() == type.getClass())
                result.add(piece);
        }
        
        return result;
    }
}
