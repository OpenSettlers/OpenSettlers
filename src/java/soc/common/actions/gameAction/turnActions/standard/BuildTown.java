package soc.common.actions.gameAction.turnActions.standard;

import java.util.ArrayList;
import java.util.List;

import soc.common.actions.gameAction.turnActions.AbstractTurnAction;
import soc.common.board.HexLocation;
import soc.common.board.HexPoint;
import soc.common.board.hexes.Hex;
import soc.common.board.hexes.ResourceHex;
import soc.common.board.hexes.SeaHex;
import soc.common.board.pieces.Town;
import soc.common.board.ports.Port;
import soc.common.board.resources.ResourceList;
import soc.common.game.Game;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.InitialPlacementGamePhase;
import soc.common.game.gamePhase.PlayTurnsGamePhase;
import soc.common.game.gamePhase.turnPhase.BuildingTurnPhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;
import soc.common.internationalization.I18n;

public class BuildTown extends AbstractTurnAction
{
    private static final long serialVersionUID = -2087932156154353767L;
    private HexPoint pointLocation;

    /**
     * @return the pointLocation
     */
    public HexPoint getPointLocation()
    {
        return pointLocation;
    }

    /**
     * @param pointLocation
     *            the pointLocation to set
     */
    public BuildTown setPointLocation(HexPoint pointLocation)
    {
        this.pointLocation = pointLocation;

        return this;
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
        // if (!(game.getCurrentPhase() instanceof InitialPlacementGamePhase))
        // {
        // boolean contains = true;
        // for (HexSide neighbour : pointLocation.getNeighbourSides())
        // {
        // if (player.getBuildPieces().getSidePieces().contains(neighbour))
        // {
        // contains = false;
        // break;
        // }
        // }
        // if (!contains)
        // {
        // invalidMessage = "No neighbouring ship or road found";
        // return false;
        // }
        // }

        // check if location is suitable (hexpoint neighbours can't be
        // already built on)

        // check if location is a valid one to built on
        // (contains at least a landhex)

        // couldnt find a neighbour, assume invalid state
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.actions.gameAction.GameAction#perform(soc.common.game.Game)
     */
    @Override
    public void perform(Game game)
    {
        // update town management
        Town town = (Town) player.getStock().getTowns().get(0);
        town.setPoint(pointLocation);
        game.addPiece(town, player);

        if (game.getCurrentPhase() instanceof PlayTurnsGamePhase)
        {
            // remove players' resources and put them in the bank
            player.getResources().moveTo(game.getBank(), town.getCost());
        }
        if (game.getCurrentPhase() instanceof InitialPlacementGamePhase
                && player.getPointPieces().size() == 2)
        {
            // player gets resources in neighbouring hexes
            ResourceList resourcesFromPlacement = new ResourceList();
            for (HexLocation loc : pointLocation.getHexLocations())
            {
                Hex hex = game.getBoard().getHexes().get(loc);
                if (hex instanceof ResourceHex)
                {
                    resourcesFromPlacement.add(((ResourceHex) hex)
                            .getResource());
                }
            }
            player.getResources().swapResourcesFrom(resourcesFromPlacement,
                    game.getBank());
        }

        // Check if the town is built on a port, if so, add port
        // to list of ports of the player

        // Get a list of ports
        List<Port> ports = new ArrayList<Port>();
        for (HexLocation locaction : pointLocation.getHexLocations())
        {
            Hex hex = game.getBoard().getHexes().get(locaction);
            if (hex instanceof SeaHex)
            {
                SeaHex seaHex = (SeaHex) hex;
                if (seaHex.getPort() != null)
                {
                    ports.add(seaHex.getPort());
                }
            }
        }
        for (Port port : ports)
        {
            if (port.getHexSide().getHexPoint1().equals(pointLocation)
                    || port.getHexSide().getHexPoint2().equals(pointLocation))
            {
                player.getPorts().add(port);
            }
        }

        message = I18n.get().actions().builtTown(player.getUser().getName());

        super.perform(game);
    }

    @Override
    public boolean isAllowed(TurnPhase turnPhase)
    {
        return turnPhase instanceof BuildingTurnPhase;
    }

    @Override
    public boolean isAllowed(GamePhase gamePhase)
    {
        return gamePhase instanceof PlayTurnsGamePhase
                || gamePhase instanceof InitialPlacementGamePhase;
    }

    @Override
    public String getToDoMessage()
    {
        return I18n.get().actions().builtTownToDo(player.getUser().getName());
    }

}
