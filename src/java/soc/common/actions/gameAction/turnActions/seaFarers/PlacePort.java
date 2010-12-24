package soc.common.actions.gameAction.turnActions.seaFarers;

import soc.common.actions.gameAction.AbstractGameAction;
import soc.common.board.ports.Port;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;

public class PlacePort extends AbstractGameAction
{
    private static final long serialVersionUID = -9211239639325281661L;
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

    @Override
    public boolean isAllowed(TurnPhase turnPhase)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isAllowed(GamePhase gamePhase)
    {
        // TODO Auto-generated method stub
        return false;
    }
}
