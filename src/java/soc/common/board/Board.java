package soc.common.board;

import java.io.Serializable;

import soc.common.board.hexes.AbstractHex;
import soc.common.board.hexes.Hex;
import soc.common.board.hexes.SeaHex;
import soc.common.board.layouts.HexGrid;
import soc.common.board.layouts.HexLayout;
import soc.common.board.ports.Port;
import soc.common.board.ports.PortList;
import soc.common.board.ports.PossiblePort;
import soc.common.board.routing.BoardGraph;
import soc.common.board.routing.GraphPoint;
import soc.common.board.routing.GraphSide;
import soc.common.board.settings.BoardSettings;
import soc.common.board.territories.TerritoryImpl;
import soc.common.board.territories.TerritoryList;
import soc.common.server.randomization.ClientRandom;
import soc.common.server.randomization.Random;

/*
 * Represents the board data structure. 

 *  A board is made up of hexes in a 2D matrix. The even rows of the matrix 
 *  have an indentation length on the left side half the width of a hex.
 * For example, a 5x5 sized board will have the layout of:

 *  <code>
 *  |H| |H| |H| |H| |H|     0
 *    |H| |H| |H| |H| |H|   1
 *  |H| |H| |H| |H| |H|     2
 *    |H| |H| |H| |H| |H|   3
 *  |H| |H| |H| |H| |H|     4
 *  </code>
 *  
 *  Sea3D has the same layout, only has the last hexes of the even rows 
 *  omitted. Thus, a Sea3D 'compatible' board would have the following
 *  layout:
 *  
 *  <code>
 *  |H| |H| |H| |H| |H|     0
 *    |H| |H| |H| |H|       1
 *  |H| |H| |H| |H| |H|     2
 *    |H| |H| |H| |H|       3
 *  |H| |H| |H| |H| |H|     4
 *  </code>
 *  
 * The last hexes of each even row in a 'Sea3D compatible' configuration
 *  should be made invisible, and/or locked.
 */

public class Board implements Serializable
{
    private static final long serialVersionUID = 5182386418039607317L;

    // list of hexes this board is made of
    protected HexGrid hexes;

    // graph containing all the GraphPoints and GraphSides
    protected transient BoardGraph graph;

    // Specific settings for this board
    protected BoardSettings boardSettings;

    protected transient Random random = new ClientRandom();

    // Name of the designer of the board
    private String designer = "Unknown player";

    // Global unique identifier for this board (UUID/GUID)
    private String id;

    // data fields
    private String name = "New Board";
    /*
     * List of territories associated with this board
     */
    protected TerritoryList territories = new TerritoryList()
            .addNew(new TerritoryImpl().setMainland(true));

    public Board(int width, int height)
    {
        this.hexes = new HexGrid(width, height);

        // Default empty board is filled with seahexes
        for (int h = 0; h < height; h++)
            for (int w = 0; w < width; w++)
                hexes.add(new SeaHex().setLocation(new HexLocation(w, h)));
    }

    /*
     * Returns a standard 3/4 player board for testing purposes as board cannot
     * be saved yet
     */
    public Board()
    {

    }

    public void initialize()
    {
        graph = new BoardGraph(this);
    }

    // / <summary>
    // / Resizes the board to a new size.
    // / </summary>
    // / <param name="newWidth">New width of the board</param>
    // / <param name="newHeight">New height of the board</param>
    public void resize(int newWidth, int newHeight, AbstractHex defaultHex)
    {
        // default on seahexes if we have no default
        if (defaultHex == null)
            defaultHex = new SeaHex();

        // return if there is nothing to resize
        if (hexes.getWidth() == newWidth && hexes.getHeight() == newHeight)
        {
            return;
        }

        // Instantiate a new board
        HexGrid newboard = new HexGrid(newWidth, newHeight);

        // HexList removedHexes = new HexList

        // loop through new sized matrix.
        for (int h = 0; h < newHeight; h++)
            for (int w = 0; w < newWidth; w++)
                // when width or height is bigger then original, add hexes
                if (w >= hexes.getWidth() || h >= hexes.getHeight())
                {
                    Hex newHex = null;

                    // if outer bounds, put a SeaHex in place, otherwise a
                    // defaulthex
                    if (w == newWidth - 1 || w == 0 || h == newHeight - 1
                            || h == 0)
                        newHex = new SeaHex();
                    else
                        newHex = defaultHex.copy();

                    newHex.setLocation(new HexLocation(w, h));
                    newboard.set(w, h, newHex);
                }
                else
                {
                    // if outer bounds, put a seahex in place,
                    // otherwise the defaulthex
                    if (w == newWidth - 1 || w == 0 || h == newHeight - 1
                            || h == 0)
                    {
                        newboard.set(w, h, new SeaHex());
                    }
                    else
                    {
                        newboard.set(w, h, defaultHex.copy());
                    }

                    newboard.set(w, h, hexes.get(w, h).copy());
                }

        hexes = newboard;
    }

    public boolean isPortBuildable(HexSide side)
    {
        Hex hex1 = hexes.get(side.getHex1());
        Hex hex2 = hexes.get(side.getHex2());
        @SuppressWarnings("unused")
        Hex landHexLocation = null;
        Hex seaHexLocation = null;
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
        if (seaHexLocation != null && seaHexLocation.canHavePort())
        {
            // A port found, invalid spot
            return false;
        }
        return true;
    }

    public PortList getAllowedPorts(AbstractHex seaHex)
    {
        PortList result = new PortList();

        // a list with all allowed ports allowed to be set at designtime
        HexLocation seaLocation = seaHex.getLocation();

        // Each SeaHex has 6 possibilities.
        PortList possibilities = new PortList();
        possibilities.add(new PossiblePort(seaLocation, RotationPosition.DEG0));
        possibilities
                .add(new PossiblePort(seaLocation, RotationPosition.DEG60));
        possibilities
                .add(new PossiblePort(seaLocation, RotationPosition.DEG120));
        possibilities
                .add(new PossiblePort(seaLocation, RotationPosition.DEG180));
        possibilities
                .add(new PossiblePort(seaLocation, RotationPosition.DEG240));
        possibilities
                .add(new PossiblePort(seaLocation, RotationPosition.DEG300));

        // Test if the other hex of the hexside is a landhex, add it to the
        // allowed
        // hexes when so.
        for (Port possibility : possibilities)
        {
            HexLocation land = possibility.getLandLocation();

            // The location must be exist on the board
            if (!hexes.isValid(land))
                // A port is placeable when the land location is actual land
                if (this.hexes.get(land).isBuildableLand())
                    result.add(possibility);
        }

        return result;
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
    public HexLayout getHexes()
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

    /**
     * @return the designer
     */
    public String getDesigner()
    {
        return designer;
    }

    /**
     * @param designer
     *            the designer to set
     */
    public Board setDesigner(String designer)
    {
        this.designer = designer;
        return this;
    }

    /**
     * @return the id
     */
    public String getId()
    {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public Board setId(String id)
    {
        this.id = id;
        return this;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public Board setName(String name)
    {
        this.name = name;
        return this;
    }

    /**
     * @return the graph
     */
    public BoardGraph getGraph()
    {
        return graph;
    }

    /*
     * Returns true when target side can be built on for land pieces
     */
    public boolean isBuildableLand(HexSide side)
    {
        return hexes.get(side.getHex1()).isBuildableLand()
                || hexes.get(side.getHex2()).isBuildableLand();
    }

    /*
     * Returns true when given point can be used to build a town, city, knight,
     * wall on
     */
    public boolean includeInGame(HexPoint point)
    {
        // We dont't want to add null
        if (point == null)
            return false;

        // HexPoint should connect to three valid hexes
        if (!point.fallsWithinBoardBounds(hexes.getWidth(), hexes.getHeight()))
            return false;

        // Hex should be able to build either sea or land on
        for (HexLocation neighbour : point.getHexLocations())
            if (hexes.get(neighbour).isBuildableLand()
                    && hexes.get(neighbour).isBuildableSea())
                return false;

        return true;
    }

    /*
     * Returns true when given point can be used to build a town on
     */
    public boolean isTownBuildable(GraphPoint possibleCandidate)
    {
        for (HexLocation location : possibleCandidate.getPoint()
                .getHexLocations())
        {
            Hex hex = hexes.get(location);
            if (!hex.isPartOfGame())
                return false;
        }
        Hex hex1 = hexes.get(possibleCandidate.getPoint().getHex1());
        Hex hex2 = hexes.get(possibleCandidate.getPoint().getHex2());
        Hex hex3 = hexes.get(possibleCandidate.getPoint().getHex3());

        if (!hex1.isBuildableLand() && !hex2.isBuildableLand()
                && !hex3.isBuildableLand())
            return false;

        return true;
    }

    /*
     * Returns true when either one of the hexes of the given side is able to
     * build for land side pieces (road)
     */
    public boolean isRoadBuildable(GraphSide neighbour)
    {
        // Grab both hexes
        Hex hex1 = hexes.get(neighbour.getSide().getHex1());
        Hex hex2 = hexes.get(neighbour.getSide().getHex2());

        // Return true when either hex is capable of hosting land side pieces
        return hex1.isBuildableLand() || hex2.isBuildableLand();
    }
}
