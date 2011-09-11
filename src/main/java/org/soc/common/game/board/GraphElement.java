package org.soc.common.game.board;

import org.soc.common.game.GamePlayer;
import org.soc.common.game.pieces.Piece.PlayerPiece;

public interface GraphElement
{
  /* Returns the player owning the element */
  public GamePlayer player();
  /* Returns the playerpiece residing on the element */
  public PlayerPiece getPiece();
  public GraphElement setPlayerPiece(PlayerPiece piece);
}
