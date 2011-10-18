package org.soc.common.game.board;

import org.soc.common.game.pieces.Piece.BoardPiece;

/*
 * For pieces on the board located at a HexPoint
 */
public interface HasPoint extends BoardPiece {
  // HEX
  public HexPoint point();
  // HAS
  public HasPoint setPoint(HexPoint point);
}
