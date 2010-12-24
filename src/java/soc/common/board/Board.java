package soc.common.board;

import soc.common.board.hexes.*;
import soc.common.board.ports.Port;
import soc.common.board.ports.PortList;
import soc.common.board.territories.Territory;
import soc.common.board.territories.TerritoryList;
import soc.common.game.GameSettings;


/// <summary>
/// Represents the board data structure. 
/// 
/// A board is made up of hexes in a 2D matrix. The even rows of the matrix 
/// have an indentation length on the left side half the width of a hex.
/// For example, a 5x5 sized board will have the layout of:
/// 
/// <code>
/// |H| |H| |H| |H| |H|     0
///   |H| |H| |H| |H| |H|   1
/// |H| |H| |H| |H| |H|     2
///   |H| |H| |H| |H| |H|   3
/// |H| |H| |H| |H| |H|     4
/// </code>
/// 
/// Sea3D has the same layout, only has the last hexes of the even rows 
/// omitted. Thus, a Sea3D 'compatible' board would have the following
/// layout:
/// 
/// <code>
/// |H| |H| |H| |H| |H|     0
///   |H| |H| |H| |H|       1
/// |H| |H| |H| |H| |H|     2
///   |H| |H| |H| |H|       3
/// |H| |H| |H| |H| |H|     4
/// </code>
/// 
/// The last hexes of each even row in a 'Sea3D compatible' configuration
/// should be made invisible, and/or locked.
/// </summary>

public class Board
{
    // list of hexes this board is made of
    private HexGrid hexes;
    
    /*
     * Specific settings for this board
     */
    private BoardSettings boardSettings;
    
    /*
     * List of territories associated with this board
     */
    private TerritoryList territories = 
        new TerritoryList()
            .addNew
            (
                new Territory()
                    .setMainland(true)
            );

    
    public Board(int width, int height)
    {
        this.hexes = new HexGrid(width,height);
        
        // Default empty board is filled with seahexes
        for (int h = 0; h < height; h++)
            for (int w = 0; w < width; w++)
                hexes.add
                (
                    new SeaHex() 
                        .setLocation(new HexLocation(w, h))
                );
    }

    /// <summary>
    /// Resizes the board to a new size. 
    /// </summary>
    /// <param name="newWidth">New width of the board</param>
    /// <param name="newHeight">New height of the board</param>
    public void Resize(int newWidth, int newHeight, AbstractHex defaultHex)
    {
        // default on seahexes if we have no default
        if (defaultHex == null) defaultHex = new SeaHex();
        
        //return if there is nothing to resize
        if (hexes.getWidth() == newWidth && hexes.getHeight() == newHeight)
        {
            return;
        }

        //Instantiate a new board
        HexGrid newboard = new HexGrid(newWidth, newHeight);
        
        //HexList removedHexes = new HexList

        //loop through new sized matrix.
        for (int h = 0; h < newHeight; h++)
        {
            for (int w = 0; w < newWidth; w++)
            {
                //when width or height is bigger then original, add hexes
                if (w >= hexes.getWidth() || h >= hexes.getHeight())
                {
                    AbstractHex newHex = null;

                    //if outer bounds, put a SeaHex in place, otherwise a defaulthex
                    if (w == newWidth - 1 || w == 0 || h == newHeight - 1 || h == 0)
                        newHex = new SeaHex();
                    else
                        newHex = defaultHex.copy();

                    newHex.setLocation(new HexLocation(w,h));
                    newboard.set(w, h, newHex);
                }
                else
                {
                    //if outer bounds, put a seahex in place, 
                    // otherwise the defaulthex
                    if (w == newWidth - 1 || w == 0 || h == newHeight - 1 || h == 0)
                    {
                        newboard.set(w, h, new SeaHex());
                    }
                    else
                    {
                        newboard.set(w, h, defaultHex.copy());
                    }

                    newboard.set(w, h, hexes.get(w, h).copy());
                }

            }
        }
        hexes = newboard;
    }
    
    public boolean isPortBuildable(HexSide side)
    {
        AbstractHex hex1 =hexes.get(side.getHex1()); 
        AbstractHex hex2 =hexes.get(side.getHex2()); 
        AbstractHex landHexLocation = null;
        AbstractHex seaHexLocation = null;
        if (hex1.isBuildableSea() && hex2.isBuildableLand())
        {
            landHexLocation = hex1;
            seaHexLocation = hex2;
        }
        if (hex2.isBuildableSea() && hex1.isBuildableLand())
        {
            landHexLocation = hex2;
            seaHexLocation = hex1;
        }
        if (seaHexLocation != null && seaHexLocation instanceof SeaHex)
        {
            // A port found, invalid spot
            if (((SeaHex)seaHexLocation).getPort() != null) return false;
        }
        return true;
    }
    

    public PortList getAllowedPorts(SeaHex seaHex)
    {
        PortList result = new PortList();
        
        // a list with all allowed ports allowed to be set at designtime
        HexLocation seaLocation = seaHex.getLocation();

        // Each SeaHex has 6 possibilities. 
        PortList possibilities = new PortList();
        possibilities.add(new Port(seaLocation, RotationPosition.DEG0));
        possibilities.add(new Port(seaLocation, RotationPosition.DEG60));
        possibilities.add(new Port(seaLocation, RotationPosition.DEG120));
        possibilities.add(new Port(seaLocation, RotationPosition.DEG180));
        possibilities.add(new Port(seaLocation, RotationPosition.DEG240));
        possibilities.add(new Port(seaLocation, RotationPosition.DEG300));
        
        // Test if the other hex of the hexside is a landhex, add it to the allowed
        // hexes when so.
        for (Port possibility : possibilities)
        {
            HexLocation land = possibility.getLandLocation(); 
            
            // The location must be exist on the board
            if (!hexes.isValid(land))
            {
                // A port is placeable when the land location is actual land
                if (this.hexes.get(land).isBuildableLand())
                {
                    result.add(possibility);
                }
            }
        }
        
        
        return result;
    }
    /// <summary>
    /// Prepares a saved board definition into a playable board.
    /// 1. Puts hexes from InitialRandomHexes list on RandomHexes
    /// 2. Replaces random ports from those out of RandomPorts bag
    /// 3. Replaces deserts by volcano/jungles if necessary
    /// </summary>
    public void PrepareForPlay(GameSettings settings)
    {
        // TODO: add code from JSettlers

    }

    /**
     * @return the boardSettings
     */
    public BoardSettings getBoardSettings()
    {
        return boardSettings;
    }

    /**
     * @return the hexes
     */
    public HexGrid getHexes()
    {
        return hexes;
    }

    /**
     * @return the width
     */
    public int getWidth()
    {
        return hexes.getWidth();
    }

    /**
     * @return the height
     */
    public int getHeight()
    {
        return hexes.getHeight();
    }

    /**
     * @return the territories
     */
    public TerritoryList getTerritories()
    {
        return territories;
    }
    
    /*
     * Returns true when target side can be built on for land pieces
     */
    public boolean isBuildableLand(HexSide side)
    {
        return 
            hexes.get(side.getHex1()).isBuildableLand() || 
            hexes.get(side.getHex2()).isBuildableLand();
    }
}
