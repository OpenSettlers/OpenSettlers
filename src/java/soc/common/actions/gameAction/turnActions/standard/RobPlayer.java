package soc.common.actions.gameAction.turnActions.standard;

import java.util.List;

import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.board.HexLocation;
import soc.common.board.HexPoint;
import soc.common.board.pieces.IPointPiece;
import soc.common.board.resources.Resource;
import soc.common.game.Game;
import soc.common.game.Player;

/*
 * An opponent is robbed of one resource caused by a 7 roll or a
 * Soldier development card play
 */
public class RobPlayer extends TurnAction
{
    // By default, the robbing player robs no one (0)
    private int victimID = 0;
    
    // Player being robbed
    private Player robbedPlayer;
    
    // Server-assigned random resource taken from the robbed player
    private Resource stolenResource;
    
    /**
     * @return Resource stolen from the player being robbed
     */
    public Resource getStolenResource()
    {
        return stolenResource;
    }

    /**
     * @param stolenResource the stolenResource to set
     */
    public RobPlayer setStolenResource(Resource stolenResource)
    {
        this.stolenResource = stolenResource;
    
        return this;
    }

    /**
     * @return Player being robbed a resource of
     */
    public Player getRobbedPlayer()
    {
        return robbedPlayer;
    }

    /**
     * @param robbedPlayer the robbedPlayer to set
     */
    public RobPlayer setRobbedPlayer(Player robbedPlayer)
    {
        this.robbedPlayer = robbedPlayer;
    
        if (robbedPlayer == null)
        {
            victimID=0;
        }
        else
        {
            victimID = robbedPlayer.getId();
        }
        
        return this;
    }

    public int getVictimID()
    {
        return victimID;
    }

    /* (non-Javadoc)
     * @see soc.common.actions.gameAction.GameAction#isValid(soc.common.game.Game)
     */
    @Override
    public boolean isValid(Game game)
    {
        if (!super.isValid(game)) return false;

        Player robbedPlayer = null;

        if (victimID != 0)
        {
            robbedPlayer = game.getPlayerByID(victimID);
        }

        // Check if the robbed player has a town or city on one of the 6 points
        if (robbedPlayer != null)
        {
            HexLocation robber = game.getRobber();
            List<HexPoint> possiblePoints = robber.getNeighbourHexPoints();
            
            boolean containsTownOrCity=false;
            for (HexPoint point : possiblePoints)
            {
                if (robbedPlayer.getTownsCities().contains(point))
                {
                    containsTownOrCity=true;
                    break;
                }
            }
            
            if (!containsTownOrCity)
            {
                invalidMessage="Robbed opponent does not have a town or city at Hexlocation" + robber.toString();
                return false;
            }
        }
        
        // 
        if (robbedPlayer !=null)
        {
            if (!robbedPlayer.getResources().hasTradeableResources())
            {
                invalidMessage = "The player should have a resource to rob";
                return false;
            }
        }
        
        return true;
    }

    /* Removes the stolen resource from the robbed player and adds it to this player
     * @see soc.common.actions.gameAction.GameAction#perform(soc.common.game.Game)
     */
    @Override
    public void perform(Game game)
    {
        player = game.getPlayerByID(sender);
        
        if (victimID != 0)
        {
            robbedPlayer = game.getPlayerByID(victimID);
        }
        
        if (robbedPlayer == null)
        {
            player.getResources().add(stolenResource);
            robbedPlayer.getResources().remove(stolenResource);
            
            message = player.getName() + " stole one " + stolenResource.toString() + " from " + robbedPlayer.getName();
        }
        else
        {
            message = player.getName() + " stole nothing! How refreshing";
        }
        
        super.perform(game);
    }
}
