package soc.common.game.variants;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import soc.common.board.pieces.abstractPieces.Piece;

public class PieceTypeList implements Iterable<Piece>
{
    private List<Piece> pieceTypes = new ArrayList<Piece>();

    @Override
    public Iterator<Piece> iterator()
    {
        return pieceTypes.iterator();
    }

    private boolean containsType(Piece type)
    {
        for (Piece piece : pieceTypes)
        {
            if (piece.getClass().equals(type.getClass()))
                return true;
        }

        return false;
    }

    public void add(Piece pieceType)
    {
        if (!containsType(pieceType))
        {
            pieceTypes.add(pieceType);
        }
    }
}
