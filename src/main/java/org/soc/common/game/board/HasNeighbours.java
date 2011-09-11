package org.soc.common.game.board;

import java.util.List;

import org.soc.common.game.board.HexSide.HexSideImpl;

public interface HasNeighbours
{
  /*
     * 
     */
  List<HexLocation> neighbourLocations();
  /*
     * 
     */
  List<HexSideImpl> neighbourSides();
  /*
     * 
     */
  List<HexPoint> neighbourPoints();
}
