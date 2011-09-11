package org.soc.common.game.board;

import org.soc.common.game.pieces.Piece.BoardPiece;

/*
 * For pieces on the board located on a HexSide.
 */
public interface HasSide extends BoardPiece
{
  public HexSide getSide();
  public HasSide setSide(HexSide side);
  public boolean canConnect(GraphPoint graphPoint, HasSide otherPiece);
  /* Returns true when this implementor can connect to a road when graphpoint is empty */
  public boolean connectsWithRoad();
  public boolean connectsWithShip();
  public boolean connectsWithBridge();
}
