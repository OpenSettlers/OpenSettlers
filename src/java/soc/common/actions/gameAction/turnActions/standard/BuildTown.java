package soc.common.actions.gameAction.turnActions.standard;

import java.util.ArrayList;
import java.util.List;

import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.board.HexLocation;
import soc.common.board.HexPoint;
import soc.common.board.HexSide;
import soc.common.board.hexes.Hex;
import soc.common.board.hexes.ResourceHex;
import soc.common.board.hexes.SeaHex;
import soc.common.board.pieces.ISidePiece;
import soc.common.board.pieces.PlayerPiece;
import soc.common.board.ports.Port;
import soc.common.game.Game;
import soc.common.game.Player;
import soc.common.game.gamePhase.InitialPlacementGamePhase;
import soc.common.game.gamePhase.PlayTurnsGamePhase;

public class BuildTown extends TurnAction
{
    private HexPoint pointLocation;
    
    /**
     * @return the pointLocation
     */
    public HexPoint getPointLocation()
    {
        return pointLocation;
    }

    /**
     * @param pointLocation the pointLocation to set
     */
    public BuildTown setPointLocation(HexPoint pointLocation)
    {
        this.pointLocation = pointLocation;
    
        return this;
    }

    /* (non-Javadoc)
     * @see soc.common.actions.gameAction.GameAction#isValid(soc.common.game.Game)
     */
    @Override
    public boolean isValid(Game game)
    {
        if (!super.isValid(game)) return false;
        
        // we need at least an instance of the new place
        if (pointLocation == null)
        {
            invalidMessage = "Location cannot be null";
            return false;
        }

        // TODO: add neighbors check
        if (game.getAllPointPieces().contains(pointLocation))
        {
            invalidMessage = "The spot and its neighbours is already used by anyone";
            return false;
        }

        // player should have a ship or road at some neighbour
        if (!(game.getCurrentPhase() instanceof InitialPlacementGamePhase))
        {
            boolean contains = true;
            for (HexSide neighbour : pointLocation.getNeighbourSides())
            {
                if (player.getBuildPieces().ofType(ISidePiece.class).contains(neighbour))
                {
                    contains = false;
                    break;
                }
            }
            if (!contains)
            {
                invalidMessage = "No neighbouring ship or road found";
                return false;
            }
        }

        // check if location is suitable (hexpoint neighbours can't be 
        // already built on)

        // check if location is a valid one to built on 
        // (contains at least a landhex)


        // couldnt find a neighbour, assume invalid state
        return true;
    }

    /* (non-Javadoc)
     * @see soc.common.actions.gameAction.GameAction#perform(soc.common.game.Game)
     */
    @Override
    public void perform(Game game)
    {
        Player player = game.getPlayerByID(sender);

        // update town management
        PlayerPiece town = player.getStock().remove(pointLocation);
        player.getBuildPieces().add(town);

        if (game.getCurrentPhase() instanceof PlayTurnsGamePhase)
        {
            // remove players' resources
            player.getResources().subtractResources(town.getCost());

            // put resources back to bank
            game.getBank().add(town.getCost());
            
            // Check if the LR should be updated
            // TODO: implement LR
            //xmlGame.CalculateLongestRoad(gamePlayer);
        }
        if (game.getCurrentPhase() instanceof InitialPlacementGamePhase &&
            player.getBuildPieces().size() == 2)
        {
            //player gets resources in neighbouring hexes

            for (HexLocation loc : pointLocation.getHexLocations())
            {
                Hex hex = game.getBoard().getHexes().get(loc);
                if (hex instanceof ResourceHex)
                {
                    player.getResources().add(((ResourceHex)hex).getResource());
                }
            }
        }

        // Check if the town is built on a port, if so, add port 
        // to list of ports of the player

        // Get a list of ports
        List<Port> ports = new ArrayList<Port>();
        for (HexLocation loc : pointLocation.getHexLocations())
        {
            Hex hex = game.getBoard().getHexes().get(loc);
            if (hex instanceof SeaHex)
            {
                SeaHex seaHex = (SeaHex)hex;
                if (((SeaHex) hex).getPort() != null)
                {
                    ports.add(seaHex.getPort());
                }
            }
        }
        for (Port port : ports)
        {
            if (port.getHexSide().getHexPoint1().equals(pointLocation) ||
                port.getHexSide().getHexPoint1().equals(pointLocation))
            {
                player.getPorts().add(port);
            }
        }
        
        // TODO: implement message
        //_Message = String.Format("{0} build a town.", 
        //    gamePlayer.XmlPlayer.Name);

        super.perform(game);
    }

}
