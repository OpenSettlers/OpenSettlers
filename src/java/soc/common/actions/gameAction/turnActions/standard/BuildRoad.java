package soc.common.actions.gameAction.turnActions.standard;

import soc.common.actions.gameAction.turnActions.AbstractTurnAction;
import soc.common.board.HexSide;
import soc.common.board.pieces.Road;
import soc.common.game.Game;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.InitialPlacementGamePhase;
import soc.common.game.gamePhase.PlayTurnsGamePhase;
import soc.common.game.gamePhase.turnPhase.BuildingTurnPhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;
import soc.common.game.player.GamePlayer;
import soc.common.internationalization.I18n;

public class BuildRoad extends AbstractTurnAction
{
    private static final long serialVersionUID = -1265027496921863516L;
    HexSide sideLocation;

    /**
     * @return the sideLocation
     */
    public HexSide getSideLocation()
    {
        return sideLocation;
    }

    /**
     * @param sideLocation
     *            the sideLocation to set
     */
    public BuildRoad setSideLocation(HexSide sideLocation)
    {
        this.sideLocation = sideLocation;

        // Enables fluent interface usage
        // http://en.wikipedia.org/wiki/Fluent_interface
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

        if (sideLocation == null)
        {
            invalidMessage = "Intersection cannot be null";
            return false;
        }

        // the spot must be free still
        if (game.getAllSidePieces().contains(sideLocation))
        {
            invalidMessage = "Already built on the given location";
            return false;
        }

        GamePlayer player = game.getPlayerByID(sender);

        if (!(game.getCurrentPhase() instanceof InitialPlacementGamePhase))
        {
            if (!(Road.ROAD.canBuild(game.getBoard(), player)))
            {
                invalidMessage = "Player cannot build the road";
                return false;
            }

            if (!(Road.ROAD.canPay(player)))
            {
                invalidMessage = "Player cannot pay for the road";
                return false;
            }
        }

        return true;
    }

    /*
     * (non-Javadoc) TODO: implement DiscoveryHexes
     * 
     * @see
     * soc.common.actions.gameAction.GameAction#perform(soc.common.game.Game)
     */
    @Override
    public void perform(Game game)
    {
        boolean usedRoadbuildingToken = false;

        Road road = (Road) player.getStock().getRoads().get(0);
        road.setSide(sideLocation);

        // when in InGame phase, player should pay for road somehow
        if (game.getCurrentPhase() instanceof PlayTurnsGamePhase)
        {
            int roadBuildingTokens = player.getRoadBuildingTokens();

            // When played a roadbuilding card, first use up roadbuilding tokens
            if (roadBuildingTokens > 0)
            {
                // the player has played a road building card this turn
                player.setRoadBuildingTokens(roadBuildingTokens--);
            }
            else
            {
                player.getResources().moveTo(game.getBank(), road.getCost());
            }

        }
        road.addToPlayer(player);

        game.getBoard().getGraph().addRoad(road);

        if (game.getCurrentPhase() instanceof PlayTurnsGamePhase)
        {
            // Check if the LR should be updated
            // TODO: do LR check
            // game.CalculateLongestRoad(pplayer);
        }

        // TODO: fix message
        // message = String.Format("{0} built a road"
        // ,game.GetPlayer(Sender).XmlPlayer.Name);

        // if (usedRoadbuildingToken)
        // _Message += ", using his roadbuilding development card.";
        message = player.getUser().getName() + " has built a road";

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
        return I18n.get().actions().builtRoadToDo(player.getUser().getName());
    }

}
