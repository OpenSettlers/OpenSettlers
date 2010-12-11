package soc.common.actions.gameAction.turnActions.standard;

import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.board.HexLocation;

/*
 * Robber is placed by current player due to 7 roll or Soldier
 * development card play
 */
public class PlaceRobber extends TurnAction
{
    private HexLocation newLocation;

    /**
     * @return the newLocation
     */
    public HexLocation getNewLocation()
    {
        return newLocation;
    }

    /**
     * @param newLocation the newLocation to set
     */
    public PlaceRobber setNewLocation(HexLocation newLocation)
    {
        this.newLocation = newLocation;
    
        // Enables fluent interface usage
        // http://en.wikipedia.org/wiki/Fluent_interface
        return this;
    }
    
}
