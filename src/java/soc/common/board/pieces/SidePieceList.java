package soc.common.board.pieces;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import soc.common.board.HexSide;

public class SidePieceList implements Iterable<SidePiece>
{
    private List<SidePiece> sidePieces = new ArrayList<SidePiece>();

    @Override
    public Iterator<SidePiece> iterator()
    {
        return sidePieces.iterator();
    }

    public void addList(SidePieceList listToAdd)
    {
        for (SidePiece sidePiece : listToAdd)
        {
            sidePieces.add(sidePiece);
        }
    }

    public boolean contains(HexSide side)
    {
        for (SidePiece sidePiece : sidePieces)
            if (sidePiece.getSide().equals(side))
                return true;

        return false;
    }

    public void add(SidePiece sidePiece)
    {
        sidePieces.add(sidePiece);
    }

    public int size()
    {
        return sidePieces.size();
    }

}
