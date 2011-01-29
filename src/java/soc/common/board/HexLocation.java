package soc.common.board;

import java.util.ArrayList;
import java.util.List;

/*
 * Represents a location of an Hex. This location is represented by
 * an w + h coordinate (width, height).
 */
public class HexLocation
{
    private int w;
    private int h;

    public int getW()
    {
        return w;
    }

    public int getH()
    {
        return h;
    }

    public HexLocation(int w, int h)
    {
        this.w = w;
        this.h = h;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        HexLocation other = (HexLocation) obj;
        if (h != other.h)
            return false;
        if (w != other.w)
            return false;
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + h;
        result = prime * result + w;
        return result;
    }

    /*
     * Returns a list containing 6 HexLocations starting with deg0 and ending
     * with deg300
     */
    public List<HexLocation> getNeighbours()
    {
        List<HexLocation> result = new ArrayList<HexLocation>();

        // add an offset for uneven rows
        int offset = h % 2 == 0 ? 0 : -1;

        result.add(new HexLocation(w + 1 + offset, h - 1));
        result.add(new HexLocation(w + 1, h));
        result.add(new HexLocation(w + 1 + offset, h + 1));
        result.add(new HexLocation(w + offset, h + 1));
        result.add(new HexLocation(w - 1, h));
        result.add(new HexLocation(w + offset, h - 1));

        return result;
    }

    public List<HexPoint> getNeighbourHexPoints()
    {
        List<HexPoint> result = new ArrayList<HexPoint>();

        // add an offset for uneven rows
        int offset = h % 2 == 0 ? 0 : -1;

        result.add(new HexPoint(this, new HexLocation(w + offset, h - 1),
                new HexLocation(w + offset + 1, h - 1)));

        result.add(new HexPoint(this, new HexLocation(w + offset + 1, h - 1),
                new HexLocation(w + 1, h)));

        result.add(new HexPoint(this, new HexLocation(w + 1, h),
                new HexLocation(w + offset + 1, h + 1)));

        result.add(new HexPoint(this, new HexLocation(w + offset + 1, h + 1),
                new HexLocation(w + offset, h + 1)));

        result.add(new HexPoint(this, new HexLocation(w + offset, h + 1),
                new HexLocation(w - 1, h)));

        result.add(new HexPoint(this, new HexLocation(w - 1, h),
                new HexLocation(w + offset, h - 1)));

        return result;
    }

    public HexSide getSideLocation(RotationPosition position)
    {
        List<HexLocation> neighbours = getNeighbours();

        switch (position)
        {
        case DEG0:
            return new HexSide(neighbours.get(0), this);
        case DEG60:
            return new HexSide(neighbours.get(1), this);
        case DEG120:
            return new HexSide(neighbours.get(2), this);
        case DEG180:
            return new HexSide(neighbours.get(3), this);
        case DEG240:
            return new HexSide(neighbours.get(4), this);
        case DEG300:
            return new HexSide(neighbours.get(5), this);
        }

        // List<HexPoint> neighbours = getNeighbourHexPoints();
        //
        // switch (position)
        // {
        // case DEG0:
        // return new HexSide(neighbours.get(5), neighbours.get(0));
        // case DEG60:
        // return new HexSide(neighbours.get(0), neighbours.get(1));
        // //
        // case DEG120:
        // return new HexSide(neighbours.get(1), neighbours.get(2));
        // case DEG180:
        // return new HexSide(neighbours.get(2), neighbours.get(3));
        // case DEG240:
        // return new HexSide(neighbours.get(3), neighbours.get(4));
        // //
        // case DEG300:
        // return new HexSide(neighbours.get(4), neighbours.get(5));
        // }

        return null;
    }

    public String toString()
    {
        return "w: " + w + "h: " + h;
    }

    public boolean fallsInsideBoardBounds(int width, int height)
    {
        if (h < 0 || w < 0)
            return false;
        if (h >= height || w >= width)
            return false;
        return true;
    }
}
