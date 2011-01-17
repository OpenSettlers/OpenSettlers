package soc.common.board.pieces;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import soc.common.board.HexPoint;

public class PointPieceList implements Iterable<PointPiece>
{
    private List<PointPiece> pointPieces = new ArrayList<PointPiece>();

    @Override
    public Iterator<PointPiece> iterator()
    {
        return pointPieces.iterator();
    }

    public void add(PointPiece pointPiece)
    {
        pointPieces.add(pointPiece);
    }

    public void remove(PointPiece pointPiece)
    {
        pointPieces.remove(pointPiece);
    }

    public void addList(PointPieceList listToAdd)
    {
        for (PointPiece pointPiece : listToAdd)
        {
            pointPieces.add(pointPiece);
        }
    }

    public boolean contains(HexPoint point)
    {
        for (PointPiece pointPiece : pointPieces)
            if (pointPiece.getPoint().equals(point))
                return true;

        return false;
    }

    public int size()
    {
        return pointPieces.size();
    }
}
