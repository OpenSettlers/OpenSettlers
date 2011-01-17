package soc.common.board.pieces;

import soc.common.annotations.SeaFarers;
import soc.common.board.Board;
import soc.common.board.HexSide;
import soc.common.board.resources.ResourceList;
import soc.common.board.resources.Sheep;
import soc.common.board.resources.Timber;
import soc.common.game.player.GamePlayer;

@SeaFarers
public class Ship extends AbstractPlayerPiece implements SidePiece
{
    private static final long serialVersionUID = -8125317569107776067L;
    public static Ship SHIP = new Ship();
    private HexSide sideLocation;

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.pieces.PlayerPiece#canBuild(soc.common.board.Board,
     * soc.common.game.Player)
     */
    @Override
    public boolean canBuild(Board board, GamePlayer player)
    {
        if (player.getStock().getShips().size() == 0)
            return false;

        // TODO: port to java
        // if (GetShipBuildPlaces(game, board).Count == 0) return false;

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.pieces.PlayerPiece#getCost()
     */
    @Override
    public ResourceList getCost()
    {
        ResourceList result = new ResourceList();

        result.add(new Timber());
        result.add(new Sheep());

        return result;
    }

    // TODO: port to java
    public boolean canMove(Board board, GamePlayer player)
    {
        // If there is no ship to move, bugger out
        // if (player.getShips().size() == 0)
        // return false;

        // when can only move a ship once per turn
        // var movedShip = (from MoveShipAction ms in
        // game.GameLog.OfType<MoveShipAction>()
        // where ms.TurnID == game.CurrentTurn
        // select ms).SingleOrDefault();
        // if (movedShip != null)
        // return false;

        // We should be able to have a moveable ship, and a location to put the
        // moved ship
        // in order to be able to move te ship
        // if (GetMoveableShips(game, game.Board).Count == 0)
        // return false;

        return true;
    }

    @Override
    public boolean isStockPiece()
    {
        return true;
    }

    @Override
    public void addToPlayer(GamePlayer player)
    {
        player.getShips().moveFrom(player.getStock().getShips(), this);
        player.getSidePieces().add(this);
    }

    @Override
    public void removeFromPlayer(GamePlayer player)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public HexSide getSide()
    {
        return sideLocation;
    }

    @Override
    public SidePiece setSide(HexSide side)
    {
        this.sideLocation = side;
        return this;
    }

}
