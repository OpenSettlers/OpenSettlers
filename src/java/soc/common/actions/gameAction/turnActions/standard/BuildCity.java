package soc.common.actions.gameAction.turnActions.standard;

import java.util.List;

import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.board.HexLocation;
import soc.common.board.HexPoint;
import soc.common.board.HexSide;
import soc.common.board.hexes.Hex;
import soc.common.board.hexes.ResourceHex;
import soc.common.board.pieces.City;
import soc.common.board.pieces.ISidePiece;
import soc.common.board.pieces.PlayerPiece;
import soc.common.board.pieces.Town;
import soc.common.board.resources.ResourceList;
import soc.common.game.Game;
import soc.common.game.Player;
import soc.common.game.gamePhase.InitialPlacementGamePhase;
import soc.common.game.gamePhase.PlayTurnsGamePhase;

public class BuildCity extends TurnAction
{
    private HexPoint pointLocation;
    
    /**
     * @return the location
     */
    public HexPoint getPointLocation()
    {
        return pointLocation;
    }

    /**
     * @param location the location to set
     */
    public BuildCity setLocation(HexPoint pointLocation)
    {
        this.pointLocation = pointLocation;
    
        return this;
    }

    /* (non-Javadoc)
     * @see soc.common.actions.gameAction.GameAction#isValid()
     */
    @Override
    public boolean isValid(Game game)
    {
        if (!super.isValid(game)) return false;

        // we need at least an instance of the new place
        if (pointLocation == null)
        {
            invalidMessage = "Location cant be null";
            return false;
        }

        // player should have a ship or road at some neighbour
        Player player = game.getPlayerByID(sender);

        // Check if we have an ISidePiece
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

        if (!(player.getBuildPieces().ofType(Town.class).contains(pointLocation)))
        {
            invalidMessage = "No town found to replace with a city";
            return false;
        }
        if (!City.CITY.canBuild(game.getBoard(), player))
        {
            invalidMessage = "Player cannot build the city";
            return false;
        }

        if (!City.CITY.canPay(player))
        {
            invalidMessage = "Player cannot pay for the city";
            return false;
        }

        return true;
    }

    /* (non-Javadoc)
     * @see soc.common.actions.gameAction.GameAction#perform(soc.common.game.Game)
     */
    @Override
    public void perform(Game game)
    {
        Player player = game.getPlayerByID(sender);
        
        if (game.getCurrentPhase() instanceof PlayTurnsGamePhase)
        {
            PlayerPiece town = player.getBuildPieces().ofType(Town.class).remove(pointLocation);
            City city = (City) player.getStock().get(City.class);
            city.setPoint(pointLocation);
            player.getBuildPieces().add(city);
            player.getResources().subtractResources(city.getCost());
            game.getBank().add(city.getCost());
            player.getStock().remove(city);
            player.getStock().add(town);
        }
        if (game.getCurrentPhase() instanceof InitialPlacementGamePhase)
        {
            PlayerPiece city = player.getStock().get(City.class);
            player.getBuildPieces().add(city);
            player.getStock().remove(city);

            ResourceList resourcesFromCity = new ResourceList();
            for (HexLocation hexLocation : pointLocation.getHexLocations())
            {
                Hex hex = game.getBoard().getHexes().get(hexLocation);
                if (hex instanceof ResourceHex)
                {
                    resourcesFromCity.add(((ResourceHex)hex).getResource());
                }
            }
            player.getResources().add(resourcesFromCity);
        }

        // TODO: fix message
        //message = String.Format("{0} build a city at {1}",
        //    gamePlayer.XmlPlayer.Name, Location.ToString(xmlGame.Board));
        super.perform(game);
    }

}
