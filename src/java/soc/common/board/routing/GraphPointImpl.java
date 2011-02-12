package soc.common.board.routing;

import soc.common.board.HexPoint;
import soc.common.board.pieces.abstractPieces.PlayerPiece;
import soc.common.board.pieces.abstractPieces.PointPiece;
import soc.common.game.player.GamePlayer;

public class GraphPointImpl implements GraphPoint
{
    private static final long serialVersionUID = 5064517666442024709L;
    private HexPoint pointLocation;
    private transient PlayerPiece playerPiece;
    private boolean townBuildable = true;

    @Override
    public HexPoint getPoint()
    {
        return pointLocation;
    }

    public GraphPoint setPoint(HexPoint point)
    {
        this.pointLocation = point;

        return this;
    }

    @Override
    public PlayerPiece getPiece()
    {
        return playerPiece;
    }

    @Override
    public GamePlayer getPlayer()
    {
        return playerPiece == null ? null : playerPiece.getPlayer();
    }

    @Override
    public GraphElement setPlayerPiece(PlayerPiece piece)
    {
        this.playerPiece = piece;

        return this;
    }

    @Override
    public boolean isTownBuildable()
    {
        return townBuildable;
    }

    @Override
    public GraphPoint setTownBuildable(boolean townBuildable)
    {
        this.townBuildable = townBuildable;

        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        return pointLocation.hashCode();
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
        if (obj instanceof HexPoint)
        {
            HexPoint hexPoint = (HexPoint) obj;
            return hexPoint.equals(pointLocation);
        }
        if (getClass() != obj.getClass())
            return false;
        GraphPointImpl other = (GraphPointImpl) obj;
        if (other.getPoint().equals(pointLocation))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public PointPiece getPointPiece()
    {
        return (PointPiece) playerPiece;
    }
}
