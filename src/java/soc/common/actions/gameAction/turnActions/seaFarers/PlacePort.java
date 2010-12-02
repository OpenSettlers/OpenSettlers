package soc.common.actions.gameAction.turnActions.seaFarers;

import soc.common.actions.gameAction.GameAction;
import soc.common.board.ports.Port;

public class PlacePort extends GameAction
{
    private int territoryID;
    private Port port;

    /**
     * @return the port
     */
    public Port getPort()
    {
        return port;
    }

    /**
     * @param port the port to set
     */
    public PlacePort setPort(Port port)
    {
        this.port = port;
    
        // Enables fluent interface usage
        // http://en.wikipedia.org/wiki/Fluent_interface
        return this;
    }

    /**
     * @return the territoryID
     */
    public int getTerritoryID()
    {
        return territoryID;
    }

    /**
     * @param territoryID the territoryID to set
     */
    public PlacePort setTerritoryID(int territoryID)
    {
        this.territoryID = territoryID;
    
        // Enables fluent interface usage
        // http://en.wikipedia.org/wiki/Fluent_interface
        return this;
    }
}
