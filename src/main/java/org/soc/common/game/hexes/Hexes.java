package org.soc.common.game.hexes;

import org.soc.common.core.GenericList.*;
import org.soc.common.core.GenericList.FullImmutableList.*;
import org.soc.common.core.GenericList.MutableList.*;
import org.soc.common.core.GenericList.MutableList.MutableModelList.*;
import org.soc.common.game.board.*;

import static org.soc.common.game.hexes.Hex.Supported.*;

public class Hexes {
  public interface Supported {
    public static final Mainland4p mainland4p = new Mainland4p();
    public static final Mainland3p mainland3p = new Mainland3p();
  }

  public final static class Mainland4p extends HexListImpl {
    public Mainland4p() {
      super(copiesOf(wheatHex, 4),
              copiesOf(timberHex, 4),
              copiesOf(oreHex, 3),
              copiesOf(sheepHex, 4),
              copiesOf(clayHex, 3));
    }
  }

  public final static class Mainland3p extends HexListImpl {
    public Mainland3p() {
      super(copiesOf(wheatHex, 3),
              copiesOf(timberHex, 3),
              copiesOf(oreHex, 3),
              copiesOf(sheepHex, 3),
              copiesOf(clayHex, 3));
    }
  }

  public static HexList copiesOf(Hex prototype, int amount) {
    MutableHexList hexes = new MutableHexListImpl();
    for (int i = 0; i < amount; i++)
      hexes.add(prototype.copy());
    return hexes.toImmutableModels();
  }

  public interface HexList extends FullImmutableList<Hex, HexLocation> {}

  public static class HexListImpl
          extends
          AbstractFullImmutableList<Hex, HexList, HexLocation>
          implements
          HexList {
    public HexListImpl(Hex... items) {
      super(items);
    }
    public HexListImpl(HexList... lists) {
      super(lists);
    }
  }

  public interface MutableHexList
          extends
          MutableModelList<Hex, HexList, HexLocation>,
          HexList {
    public HexList toImmutableModels();
  }

  public static class MutableHexListImpl
          extends
          FullMutableListImpl<Hex, HexList, HexLocation>
          implements
          MutableHexList {
    @Override public HexList toImmutableModels() {
      return new HexListImpl(this);
    }
  }
}
