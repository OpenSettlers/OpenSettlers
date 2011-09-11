package org.soc.common.game.pieces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.soc.common.game.hexes.Hex;
import org.soc.common.game.pieces.Piece.Producable;

public class ProducerList implements Iterable<Producable>, Serializable {
  private static final long serialVersionUID = 2619202676402607856L;
  private List<Producable> producables = new ArrayList<Producable>();

  public void add(Producable producable) {
    producables.add(producable);
  }
  public void remove(Producable producable) {
    producables.remove(producable);
  }
  public ProducerList producablesAtHex(Hex hex) {
    ProducerList result = new ProducerList();
    for (Producable producable : producables)
      if (producable.point().hasLocation(hex.location()))
        result.add(producable);
    return result;
  }
  @Override public Iterator<Producable> iterator() {
    return producables.iterator();
  }
}
