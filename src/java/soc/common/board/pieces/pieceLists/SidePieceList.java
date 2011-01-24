package soc.common.board.pieces.pieceLists;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import soc.common.board.HexSide;
import soc.common.board.pieces.abstractPieces.SidePiece;

public class SidePieceList implements Iterable<SidePiece>
{
    private List<SidePiece> sidePieces = new ArrayList<SidePiece>();
    private List<HexSide> sides = new ArrayList<HexSide>();

    @Override
    public Iterator<SidePiece> iterator()
    {
        return sidePieces.iterator();
    }

    public void addList(SidePieceList listToAdd)
    {
        for (SidePiece sidePiece : listToAdd)
        {
            add(sidePiece);
        }
    }

    public boolean contains(HexSide side)
    {
        return sides.contains(side);
    }

    public void add(SidePiece sidePiece)
    {
        sidePieces.add(sidePiece);
        sides.add(sidePiece.getSide());
    }

    public SidePiece get(HexSide side)
    {
        return sidePieces.get(sides.indexOf(side));
    }

    public int size()
    {
        return sidePieces.size();
    }

    public List<HexSide> getSides()
    {
        return sides;
    }

}
