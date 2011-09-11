package org.soc.common.game.board;

import org.soc.common.game.GamePlayer;
import org.soc.common.game.pieces.Piece.PlayerPiece;

public interface GraphSide extends GraphElement
{
  public HexSide side();
  public boolean isBuildable();
  public HasSide getSidePiece();

  public class GraphSideImpl implements GraphSide
  {
    private HexSide sideLocation;
    private PlayerPiece playerPiece;

    public GraphSideImpl(HexSide sideLocation)
    {
      super();
      this.sideLocation = sideLocation;
    }
    @Override public HexSide side()
    {
      return sideLocation;
    }
    public GraphSideImpl setSide(HexSide side)
    {
      this.sideLocation = side;
      return this;
    }
    @Override public PlayerPiece getPiece()
    {
      return playerPiece;
    }
    @Override public GamePlayer player()
    {
      return playerPiece == null ? null : playerPiece.player();
    }
    @Override public GraphElement setPlayerPiece(PlayerPiece piece)
    {
      this.playerPiece = piece;
      return this;
    }
    @Override public boolean isBuildable()
    {
      return playerPiece == null;
    }
    @Override public int hashCode()
    {
      return sideLocation.hashCode();
    }
    @Override public boolean equals(Object obj)
    {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (obj instanceof HexSide)
      {
        return sideLocation.equals(((HexSide) obj));
      }
      if (getClass() != obj.getClass())
        return false;
      GraphSideImpl other = (GraphSideImpl) obj;
      if (sideLocation == null)
      {
        if (other.sideLocation != null)
          return false;
      } else if (!sideLocation.equals(other.sideLocation))
        return false;
      return true;
    }
    @Override public HasSide getSidePiece()
    {
      return (HasSide) playerPiece;
    }
  }
}
