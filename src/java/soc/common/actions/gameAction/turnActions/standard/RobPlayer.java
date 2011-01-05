package soc.common.actions.gameAction.turnActions.standard;

import java.util.List;

import soc.common.actions.gameAction.turnActions.AbstractTurnAction;
import soc.common.board.HexPoint;
import soc.common.board.resources.Resource;
import soc.common.game.Game;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.PlayTurnsGamePhase;
import soc.common.game.gamePhase.turnPhase.BeforeDiceRollTurnPhase;
import soc.common.game.gamePhase.turnPhase.BuildingTurnPhase;
import soc.common.game.gamePhase.turnPhase.RollDiceTurnPhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;
import soc.common.game.player.GamePlayer;
import soc.common.internationalization.I18n;

/*
 * An opponent is robbed of one resource caused by a 7 roll or a
 * Soldier development card play
 */
public class RobPlayer extends AbstractTurnAction
{
    private static final long serialVersionUID = 608382763517355301L;

    // By default, the robbing player robs no one (0)
    private int victimID = 0;

    // Player being robbed
    private GamePlayer robbedPlayer;

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
     * @param stolenResource
     *            the stolenResource to set
     */
    public RobPlayer setStolenResource(Resource stolenResource)
    {
        this.stolenResource = stolenResource;

        return this;
    }

    /**
     * @return Player being robbed a resource of
     */
    public GamePlayer getRobbedPlayer()
    {
        return robbedPlayer;
    }

    /**
     * @param robbedPlayer
     *            the robbedPlayer to set
     */
    public RobPlayer setRobbedPlayer(GamePlayer robbedPlayer)
    {
        this.robbedPlayer = robbedPlayer;

        if (robbedPlayer == null)
        {
            victimID = 0;
        }
        else
        {
            victimID = robbedPlayer.getUser().getId();
        }

        return this;
    }

    public int getVictimID()
    {
        return victimID;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.actions.gameAction.GameAction#isValid(soc.common.game.Game)
     */
    @Override
    public boolean isValid(Game game)
    {
        if (!super.isValid(game))
            return false;

        if (victimID != 0)
        {
            robbedPlayer = game.getPlayerByID(victimID);
        }

        // Checks to do if a player is robbed
        if (robbedPlayer != null)
        {
            // Check if the robbed player has a town or city on one of the 6
            // points
            List<HexPoint> possiblePoints = game.getRobber().getLocation()
                    .getNeighbourHexPoints();

            boolean containsTownOrCity = false;
            for (HexPoint point : possiblePoints)
            {
                if (robbedPlayer.getTownsCities().contains(point))
                {
                    containsTownOrCity = true;
                    break;
                }
            }

            if (!containsTownOrCity)
            {
                invalidMessage = "Robbed opponent does not have a town or city at Hexlocation"
                        + game.getRobber().toString();
                return false;
            }

            if (!robbedPlayer.getResources().hasTradeableResources())
            {
                invalidMessage = "The player should have a resource to rob";
                return false;
            }
        }

        return true;
    }

    /*
     * Removes the stolen resource from the robbed player and adds it to this
     * player
     * 
     * @see
     * soc.common.actions.gameAction.GameAction#perform(soc.common.game.Game)
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

            message = player.getUser().getName() + " stole one "
                    + stolenResource.toString() + " from "
                    + robbedPlayer.getUser().getName();
        }
        else
        {
            message = player.getUser().getName()
                    + " stole nothing! How refreshing";
        }

        super.perform(game);
    }

    @Override
    public boolean isAllowed(TurnPhase turnPhase)
    {
        return turnPhase instanceof BeforeDiceRollTurnPhase
                || turnPhase instanceof RollDiceTurnPhase
                || turnPhase instanceof BuildingTurnPhase;
    }

    @Override
    public boolean isAllowed(GamePhase gamePhase)
    {
        return gamePhase instanceof PlayTurnsGamePhase;
    }

    @Override
    public String getToDoMessage()
    {
        return I18n.get().actions().robPlayerToDo(player.getUser().getName());
    }
}
