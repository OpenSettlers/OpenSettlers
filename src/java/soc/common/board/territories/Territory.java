package soc.common.board.territories;

import soc.common.annotations.SeaFarers;
import soc.common.board.ports.PortList;

/*
 * Represents a group of LandHexes. A territory is useful for:
 * - Trade routes
 * - Chit swapping
 * - Bonus island VPs
 * 
 */
@SeaFarers
public class Territory
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
    
    /**
     * @return the isBonus
     */
    public boolean isBonus()
    {
        return isBonus;
    }
    /**
     * @param isBonus the isBonus to set
     */
    public Territory setBonus(boolean isBonus)
    {
        this.isBonus = isBonus;
    
        // Enables fluent interface usage
        // http://en.wikipedia.org/wiki/Fluent_interface
        return this;
    }
    
    public String getName()
    {
        return name;
    }
    /**
     * @return the ports
     */
    public PortList getPorts()
    {
        return ports;
    }
    /**
     * @param ports the ports to set
     */
    public Territory setPorts(PortList ports)
    {
        this.ports = ports;
    
        return this;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public int getID()
    {
        return ID;
    }
    public Territory setID(int iD)
    {
        ID = iD;
        
        return this;
    }
    public boolean isMainland()
    {
        return isMainland;
    }
    public Territory setMainland(boolean isMainland)
    {
        this.isMainland = isMainland;

        isIsland= !isMainland();
        
        return this;
    }
    public boolean isIsland()
    {
        return isIsland;
    }
    public Territory setIsland(boolean isIsland)
    {
        this.isIsland = isIsland;
        
        isIsland = !isMainland();
        
        return this;
    }
}
