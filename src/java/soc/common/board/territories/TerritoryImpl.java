package soc.common.board.territories;

import soc.common.annotations.SeaFarers;
import soc.common.board.ChitList;
import soc.common.board.hexes.HexList;
import soc.common.board.ports.PortList;

/*
 * Represents a group of LandHexes. A territory is useful for:
 * - Trade routes
 * - Chit swapping
 * - Bonus island VPs
 */
@SeaFarers
public class TerritoryImpl implements Territory
{
    private String name;
    private int ID;
    private boolean isMainland;
    private boolean isIsland;

    // Whether or not a bonus vp can be earned while building on it
    private boolean isBonus;

    // A territory may have a list of ports associated with them.
    // These may be placed by players in a PlacePortGamePhase,
    // or before start by the server, to replace randomports with.
    private PortList ports;

    /*
     * List of chits for this territory, used at board generation time
     */
    private ChitList chits = new ChitList();

    /*
     * List of hexes belonging to this territory. Used for: - Board generation
     * time to replace RandomHexes with - DiscoveryHexes to replace
     * DiscoveryHexes with
     */
    private HexList hexes = new HexList();

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.territories.Territory#isBonus()
     */
    public boolean isBonus()
    {
        return isBonus;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.territories.Territory#setBonus(boolean)
     */
    public Territory setBonus(boolean isBonus)
    {
        this.isBonus = isBonus;
        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.territories.Territory#getName()
     */
    public String getName()
    {
        return name;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.territories.Territory#getPorts()
     */
    public PortList getPorts()
    {
        return ports;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.board.territories.Territory#setPorts(soc.common.board.ports
     * .PortList)
     */
    public Territory setPorts(PortList ports)
    {
        this.ports = ports;
        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.territories.Territory#setName(java.lang.String)
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.territories.Territory#getID()
     */
    public int getID()
    {
        return ID;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.territories.Territory#setID(int)
     */
    public Territory setID(int iD)
    {
        ID = iD;
        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.territories.Territory#isMainland()
     */
    public boolean isMainland()
    {
        return isMainland;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.territories.Territory#setMainland(boolean)
     */
    public TerritoryImpl setMainland(boolean isMainland)
    {
        this.isMainland = isMainland;
        isIsland = !isMainland();
        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.territories.Territory#isIsland()
     */
    public boolean isIsland()
    {
        return isIsland;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.territories.Territory#setIsland(boolean)
     */
    public Territory setIsland(boolean isIsland)
    {
        this.isIsland = isIsland;
        isIsland = !isMainland();
        return this;
    }

    @Override
    public ChitList getChits()
    {
        return chits;
    }

    @Override
    public HexList getHexes()
    {
        return hexes;
    }
}
