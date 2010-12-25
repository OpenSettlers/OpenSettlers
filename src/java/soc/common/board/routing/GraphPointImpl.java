package soc.common.board.routing;

import soc.common.board.HexPoint;
import soc.common.board.pieces.PlayerPiece;
import soc.common.game.Player;

public class GraphPointImpl implements GraphPoint
{
    private HexPoint pointLocation;
    private PlayerPiece playerPiece;
    private boolean townBuildable;

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
    public Player getPlayer()
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

}
