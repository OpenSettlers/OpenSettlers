package org.soc.common.board.layout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.soc.common.board.HexChangedEvent;
import org.soc.common.board.HexChangedEventHandler;
import org.soc.common.board.hexes.Hex;


import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;

/*
 * Keeps hexes in a list of size = width * height
 */
public class HexGrid implements HexLayout
{
    private static final long serialVersionUID = 8931931419267340946L;
    private int width;
    private int height;
    private ArrayList<Hex> hexes = new ArrayList<Hex>();
    protected transient SimpleEventBus eventBus = new SimpleEventBus();

    public int getHeight()
    {
        return width;
    }

    public int getWidth()
    {
        return height;
    }

    public HexGrid()
    {
        // Default instantiable constructor
    }

    public HexGrid(int width, int height)
    {
        // Set the capacity of the list only once to increase performance
        hexes.ensureCapacity(width * height);

        // Set the height & width fields
        this.width = width;
        this.height = height;
    }

    public Hex get(int w, int h)
    {
        if (!checkInput(w, h))
            return null;

        return hexes.get((width * h) + w);
    }

    public void set(int w, int h, Hex value)
    {
        checkInput(w, h);

        if (hexes.size() - 1 < (width * h) + w)
        {
            hexes.set((width * h) + w, value);
        } else
        {
            Hex oldHex = hexes.get((width * h) + w);
            hexes.set((width * h) + w, value);
            hexes.get((width * h) + w).setLocation(new HexLocation(w, h));
            eventBus.fireEvent(new HexChangedEvent(oldHex, value));
        }
    }

    public boolean checkInput(int w, int h)
    {
        if (w < 0 || h < 0 || w >= width || h >= height)
        {
            throw new RuntimeException("Can't add location out of bounds array");
        }
        return true;
    }

    private boolean checkInput(HexLocation location)
    {
        return checkInput(location.getW(), location.getH());
    }

    public Hex get(HexLocation location)
    {
        if (!checkInput(location))
            return null;
        return get(location.getW(), location.getH());
    }

    public void set(HexLocation location, Hex value)
    {
        checkInput(location.getW(), location.getH());
        set(location.getW(), location.getH(), value);
    }

    /*
     * Return true when specified HexLocation can be contained within the
     * HexList
     */
    public boolean isValid(HexLocation hexLocation)
    {
        // when location falls outside west & north bounds, return invalid
        if (width < 0 || height < 0 || hexLocation.getH() >= height
                        || hexLocation.getW() >= width)
            return false;
        else
            return true;
    }

    /*
     * Returns true if given location is at the edge of the board.
     */
    public boolean isAtEdge(HexLocation location)
    {
        if (location.getH() == 0 || location.getW() == 0
                        || location.getH() >= height
                        || location.getW() >= width)
            return true;
        else
            return false;
    }

    public HandlerRegistration addHexChangedHandler(
                    HexChangedEventHandler handler)
    {
        return eventBus.addHandler(HexChangedEvent.TYPE, handler);
    }

    public List<Hex> getValidNeighbours(Hex hex)
    {
        List<Hex> neighbours = new ArrayList<Hex>();

        for (HexLocation neighbour : hex.getLocation().getNeighbours())
            if (isValid(neighbour))
                neighbours.add(get(neighbour));

        return neighbours;
    }

    @Override
    public Iterator<Hex> iterator()
    {
        return hexes.iterator();
    }

    public void add(Hex hex)
    {
        hexes.add(hex);
    }
}
