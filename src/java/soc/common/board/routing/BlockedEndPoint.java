package soc.common.board.routing;

import soc.common.board.HexPoint;
import soc.common.board.pieces.abstractPieces.PlayerPiece;
import soc.common.board.pieces.abstractPieces.PointPiece;
import soc.common.game.player.GamePlayer;

import com.google.gwt.user.client.Random;

/*
 * Represents a splitted end point of a subgraph. When a town is built on an existing 
 * route of a different player, this point would be added twice to the routes' list of 
 * vertices. This violates the constraint of graphs that each point should be unique 
 * and never be added twice. A SplittedEndPoint becomes the endpoint of the two roads
 * connecting to the point now owned by the player building a town on the existing road.  
 */
public class BlockedEndPoint implements GraphPoint
{
    private HexPoint pointLocation;
    private int hashCode = Random.nextInt();

    @Override
    public HexPoint getPoint()
    {
        return pointLocation;
    }

    public BlockedEndPoint(HexPoint pointLocation)
    {
        this.pointLocation = pointLocation;
    }

    /*
     * Returns false, since a splittedEndPoint is created because the original
     * point is causing a split
     * 
     * @see soc.common.board.routing.IGraphPoint#isTownBuildable()
     */
    @Override
    public boolean isTownBuildable()
    {
        return false;
    }

    /*
     * Ignored
     * 
     * @see soc.common.board.routing.IGraphPoint#setTownBuildable(boolean)
     */
    @Override
    public GraphPoint setTownBuildable(boolean townBuildable)
    {
        return this;
    }

    /*
     * Returns always null
     * 
     * @see soc.common.board.routing.IGraphElement#getPiece()
     */
    @Override
    public PlayerPiece getPiece()
    {
        return null;
    }

    /*
     * Returns always null
     * 
     * @see soc.common.board.routing.IGraphElement#getPlayer()
     */
    @Override
    public GamePlayer getPlayer()
    {
        return null;
    }

    /*
     * Ignored
     * 
     * @see
     * soc.common.board.routing.IGraphElement#setPlayerPiece(soc.common.board
     * .pieces.PlayerPiece)
     */
    @Override
    public GraphElement setPlayerPiece(PlayerPiece piece)
    {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        return hashCode;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BlockedEndPoint other = (BlockedEndPoint) obj;
        if (hashCode != other.hashCode())
            return false;
        return true;
    }

    @Override
    public PointPiece getPointPiece()
    {
        return null;
    }

}
