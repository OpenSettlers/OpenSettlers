package soc.common.board.pieces.pieceLists;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import soc.common.board.HexPoint;
import soc.common.board.pieces.abstractPieces.PointPiece;

public class PointPieceList implements Iterable<PointPiece>, Serializable
{
    private static final long serialVersionUID = -5650123247361039636L;
    private List<PointPiece> pointPieces = new ArrayList<PointPiece>();
    private List<HexPoint> points = new ArrayList<HexPoint>();

    @Override
    public Iterator<PointPiece> iterator()
    {
        return pointPieces.iterator();
    }

    public void add(PointPiece pointPiece)
    {
        pointPieces.add(pointPiece);
        points.add(pointPiece.getPoint());
    }

    public void remove(PointPiece pointPiece)
    {
        pointPieces.remove(pointPiece);
        points.remove(pointPiece.getPoint());
    }

    public void addList(PointPieceList listToAdd)
    {
        for (PointPiece pointPiece : listToAdd)
            add(pointPiece);
    }

    public boolean contains(HexPoint point)
    {
        return points.contains(point);
    }

    public int size()
    {
        return pointPieces.size();
    }

    public List<HexPoint> getPoints()
    {
        return points;
    }
}
