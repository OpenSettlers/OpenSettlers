package soc.common.board;

import java.util.ArrayList;

import soc.common.board.hexes.Hex;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;

public class HexGrid extends ArrayList<Hex>
{
    private static final long serialVersionUID = 8931931419267340946L;
    private final int width;
    private final int height;
    protected transient SimpleEventBus eventBus = new SimpleEventBus();

    public int getHeight()
    {
        return width;
    }

    public int getWidth()
    {
        return height;
    }

    public HexGrid(int w, int h)
    {
        // Set the capacity of the list only once to increase performance
        this.ensureCapacity(w * h);

        // Initialize empty
        for (int i = w * h; i < w * h; i++)
        {
            super.set(i, null);
        }

        // Set the height & width fields
        width = w;
        height = h;
    }

    public Hex get(int w, int h)
    {
        if (!checkInput(w, h))
            return null;

        return get((width * h) + w);
    }

    public void set(int w, int h, Hex value)
    {
        checkInput(w, h);

        if (size() - 1 < (width * h) + w)
        {
            super.set((width * h) + w, value);
        }
        else
        {
            Hex oldHex = get((width * h) + w);
            set((width * h) + w, value);
            get((width * h) + w).setLocation(new HexLocation(w, h));
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
        {
            return false;
        }

        return true;
    }

    /*
     * Returns true if given location is at the edge of the board.
     */
    public boolean isAtEdge(HexLocation location)
    {
        if (location.getH() == 0 || location.getW() == 0
                || location.getH() >= height || location.getW() >= width)
        {
            return true;
        }
        return false;
    }

    public HandlerRegistration addHexChangedHandler(
            HexChangedEventHandler handler)
    {
        return eventBus.addHandler(HexChangedEvent.TYPE, handler);
    }
}
