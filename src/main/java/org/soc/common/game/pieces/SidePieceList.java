package org.soc.common.game.pieces;

import java.io.*;
import java.util.*;

import org.soc.common.game.board.*;

public class SidePieceList implements Iterable<HasSide>, Serializable {
  private List<HasSide> sidePieces = new ArrayList<HasSide>();
  private List<HexSide> sides = new ArrayList<HexSide>();

  @Override public Iterator<HasSide> iterator() {
    return sidePieces.iterator();
  }
  public void addList(SidePieceList listToAdd) {
    for (HasSide sidePiece : listToAdd) {
      add(sidePiece);
    }
  }
  public boolean contains(HexSide side) {
    return sides.contains(side);
  }
  public void add(HasSide sidePiece) {
    sidePieces.add(sidePiece);
    sides.add(sidePiece.getSide());
  }
  public HasSide get(HexSide side) {
    return sidePieces.get(sides.indexOf(side));
  }
  public int size() {
    return sidePieces.size();
  }
  public List<HexSide> getSides() {
    return sides;
  }
  public boolean containsNeighbourSide(HexPoint point) {
    for (HexSide side : point.neighbourSides())
      if (contains(side))
        return true;
    return false;
  }
}
