package org.soc.common.game.pieces;

import java.io.*;
import java.util.*;

import org.soc.common.game.hexes.*;
import org.soc.common.game.pieces.Piece.Producer;

public class ProducerList implements Iterable<Producer>, Serializable {
  private static final long serialVersionUID = 2619202676402607856L;
  private List<Producer> producers = new ArrayList<Producer>();

  public void add(Producer producable) {
    producers.add(producable);
  }
  public void remove(Producer producable) {
    producers.remove(producable);
  }
  public ProducerList producersAtHex(Hex hex) {
    ProducerList result = new ProducerList();
    for (Producer producer : producers)
      if (producer.point().hasLocation(hex.location()))
        result.add(producer);
    return result;
  }
  @Override public Iterator<Producer> iterator() {
    return producers.iterator();
  }
}
