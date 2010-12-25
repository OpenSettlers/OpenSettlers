package soc.common.board.routing;

import soc.common.board.HexSide;
import soc.common.board.pieces.PlayerPiece;
import soc.common.game.Player;

public class GraphSideImpl implements GraphSide
{
    private HexSide sideLocation;
    private PlayerPiece playerPiece;
    
    @Override
    public HexSide getSide()
    {
        return sideLocation;
    }
    public GraphSideImpl setSide(HexSide side)
    {
        this.sideLocation=side;
        
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
        return playerPiece==null ? 
                null : playerPiece.getPlayer();
    }

    @Override
    public GraphElement setPlayerPiece(PlayerPiece piece)
    {
        this.playerPiece=piece;
        
        return this;
    }
    @Override
    public boolean isBuildable()
    {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public GraphPoint setBuildable(boolean townBuildable)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
