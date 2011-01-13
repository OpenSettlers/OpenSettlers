package soc.common.board.pieces;

import soc.common.board.Board;
import soc.common.board.HexPoint;
import soc.common.board.resources.Clay;
import soc.common.board.resources.ResourceList;
import soc.common.board.resources.Sheep;
import soc.common.board.resources.Timber;
import soc.common.board.resources.Wheat;
import soc.common.game.VictoryPointItem;
import soc.common.game.player.GamePlayer;

public class Town extends AbstractPlayerPiece implements VictoryPointItem,
        PointPiece
{
    private static final long serialVersionUID = -2696233711789990786L;
    public static Town TOWN = new Town();
    private HexPoint pointLocation;

    @Override
    public String toString()
    {
        return "Town";
    }

    @Override
    public ResourceList getCost()
    {
        ResourceList result = new ResourceList();

        result.add(new Timber());
        result.add(new Wheat());
        result.add(new Clay());
        result.add(new Sheep());

        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.pieces.PlayerPiece#canBuild(soc.common.board.Board,
     * soc.common.game.Player)
     */
    @Override
    public boolean canBuild(Board board, GamePlayer player)
    {
        // We need a town in stock...
        if (player.getStock().ofType(Town.TOWN).size() == 0)
            return false;

        return true;
    }

    @Override
    public int getVictoryPoints()
    {
        return 1;
    }

    @Override
    public HexPoint getPoint()
    {
        return pointLocation;
    }

    @Override
    public PointPiece setPoint(HexPoint point)
    {
        this.pointLocation = point;

        return this;
    }

    @Override
    public boolean isStockPiece()
    {
        return true;
    }

    @Override
    public void addToPlayer(GamePlayer player)
    {
        PlayerPieceList.move(this, player.getStock(), player.getBuildPieces());
        player.getVictoryPoints().add(this);
    }

    @Override
    public void removeFromPlayer(GamePlayer player)
    {
        PlayerPieceList.move(this, player.getBuildPieces(), player.getStock());
        player.getVictoryPoints().remove(this);
    }
}
