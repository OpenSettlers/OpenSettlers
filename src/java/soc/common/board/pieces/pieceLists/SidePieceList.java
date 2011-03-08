package soc.common.board.pieces.pieceLists;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import soc.common.board.layout.HasSide;
import soc.common.board.layout.HexSide;

public class SidePieceList implements Iterable<HasSide>, Serializable
{
    private static final long serialVersionUID = 7047263447536155603L;
    private List<HasSide> sidePieces = new ArrayList<HasSide>();
    private List<HexSide> sides = new ArrayList<HexSide>();

    @Override
    public Iterator<HasSide> iterator()
    {
        return sidePieces.iterator();
    }

    public void addList(SidePieceList listToAdd)
    {
        for (HasSide sidePiece : listToAdd)
        {
            add(sidePiece);
        }
    }

    public boolean contains(HexSide side)
    {
        return sides.contains(side);
    }

    public void add(HasSide sidePiece)
    {
        sidePieces.add(sidePiece);
        sides.add(sidePiece.getSide());
    }

    public HasSide get(HexSide side)
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
