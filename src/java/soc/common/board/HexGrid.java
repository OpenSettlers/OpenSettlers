package soc.common.board;

import java.util.ArrayList;

import soc.common.board.hexes.AbstractHex;

public class HexGrid extends ArrayList<AbstractHex>
{
    private final int width;
    private final int height;
    
    public int getHeight()
    {
        return width;
    }
    
    public int getWidth()
    {
        return height;
    }
    
    HexGrid(int w, int h)
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
    
    public AbstractHex get(int w, int h)
    {
        if (!checkInput(w, h)) return null;
        
        return get((width * h) + w);
    }
    
    public void set(int w, int h, AbstractHex value)
    {
        checkInput(w, h);
        
        if (size() - 1 < (width * h) + w)
        {
            super.set((width * h) + w, value);
        }
        else
        {
            // Oldhex needed for obsrvable listeners
            AbstractHex oldHex = get((width * h) + w);
            set((width * h) + w, value);
            get((width * h) + w).setLocation(new HexLocation(w, h));
            // TODO: make observable
            //OnHexChanged(temp, value);
        }
    }
    
    public boolean checkInput(int w, int h)
    {
        if (w < 0 || h < 0 || w >= width ||h >= height) 
        {
            throw new RuntimeException("Can't add location out of bounds array");
        }
        return true;
    }
    
    private boolean checkInput(HexLocation location)
    {
        return checkInput(location.getW(), location.getH());
    }

    public AbstractHex get(HexLocation location)
    {
        if (!checkInput(location)) return null;
        return get(location.getW(), location.getH());
    }
        
    public void set(HexLocation location, AbstractHex value)
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
        if (width < 0 || 
            height < 0 ||
            hexLocation.getH() >= height ||
            hexLocation.getW() >= width)
        {
            return false;
        }
        
        return true;
    }
    
    /*
     * Returns true if given location is at the edge of the board
     * TODO: support NoneHexes
     * NoneHexes are not-existing in the game, therefore must be treated as out of map.
     * Thus, the hex neighbouring the NoneHex is located at the edge. Then below function
     * will not suit. 
     */
    public boolean isAtEdge(HexLocation location)
    {
        if (location.getH() == 0 ||
                location.getW() == 0 ||
                location.getH() >= height ||
                location.getW() >= width)
            {
                return false;
            }
        return true;
    }
}
