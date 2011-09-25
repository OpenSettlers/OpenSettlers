package org.soc.common.game.board;

import java.util.Set;

import org.soc.common.game.GamePlayer;

public interface BuildPossibilities<T> {
  public Set<T> buildPossibitiesFor(GamePlayer player, GameBoard gameBoard);
}
