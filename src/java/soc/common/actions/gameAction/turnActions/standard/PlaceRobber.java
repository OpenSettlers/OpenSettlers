package soc.common.actions.gameAction.turnActions.standard;

import soc.common.actions.gameAction.turnActions.AbstractTurnAction;
import soc.common.board.HexLocation;
import soc.common.board.hexes.DiscoveryHex;
import soc.common.board.hexes.Hex;
import soc.common.board.hexes.NoneHex;
import soc.common.board.hexes.RandomHex;
import soc.common.board.hexes.SeaHex;
import soc.common.game.Game;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;
import soc.common.internationalization.I18n;

/*
 * Robber is placed by current player due to 7 roll or Soldier
 * development card play
 */
public class PlaceRobber extends AbstractTurnAction
{
    private static final long serialVersionUID = 3908846616233400447L;
    private HexLocation newLocation;

    /**
     * @return the newLocation
     */
    public HexLocation getNewLocation()
    {
        return newLocation;
    }

    /**
     * @param newLocation
     *            the newLocation to set
     */
    public PlaceRobber setNewLocation(HexLocation newLocation)
    {
        this.newLocation = newLocation;

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

        // we need a good location
        if (newLocation == null)
        {
            invalidMessage = "Location can't be null";
            return false;
        }

        // Make sure the player does not put robber or pirate on the edge of the
        // map,
        // which is forbidden
        if (game.getBoard().getHexes().isAtEdge(newLocation))
        {
            invalidMessage = "putting robber on the edge is not allowed";
            return false;
        }

        // TODO: check if a previous action included rolling a 7,
        // or playing a soldier development card

        // Player may not leave the robber on the same location
        if (game.getRobber().equals(newLocation))
        {
            invalidMessage = "putting robber back onto same location is not allowed";
            return false;
        }

        // valid hexes include:
        // -seahexes for pirate
        // -ordinary resource hexes
        // -volcano's
        // -jungle's
        // 
        // invalid places include:
        // -Randomhex
        // -Nonehex
        // -DiscoveryHex
        // -

        Hex hex = game.getBoard().getHexes().get(newLocation);

        if (hex instanceof NoneHex || hex instanceof DiscoveryHex
                || hex instanceof RandomHex || hex instanceof SeaHex)
        {
            invalidMessage = "Can't place robber or pirate on a "
                    + hex.getName();
            return false;
        }

        return super.isValid(game);
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
        game.setRobber(newLocation);

        // TODO: fix message
        // _Message = String.Format("{0} put the robber on the {1}",
        // xmlGame.GetPlayer(Sender).XmlPlayer.Name,
        // Location.ToString(xmlGame.Board));
        super.perform(game);
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

    @Override
    public String getToDoMessage()
    {
        return I18n.get().actions().placeRobberToDo(player.getName());
    }

}
