package org.soc.common.game;

import org.soc.common.core.GenericList.FullImmutableList;
import org.soc.common.core.GenericList.FullImmutableList.AbstractFullImmutableList;
import org.soc.common.core.GenericList.MutableList.MutableModelList;
import org.soc.common.core.GenericList.MutableList.MutableModelList.FullMutableListImpl;

import static org.soc.common.game.Chit.Supported.*;

public class Chits {
  public interface Supported {
    public static final ChitList standard3pChits = new Standard3pChits();
    public static final ChitList swapChits = new SwapChits();
    public static final ChitList standard4pChits = new Standard4pChits();
  }

  public static final class Standard4pChits extends ChitListImpl implements ChitList {
    public Standard4pChits() {
      super(one(chit2),
              copiesOf(chit3, 2),
              copiesOf(chit4, 2),
              copiesOf(chit5, 2),
              copiesOf(chit6, 2),
              copiesOf(chit8, 2),
              copiesOf(chit9, 2),
              copiesOf(chit10, 2),
              copiesOf(chit11, 2),
              one(chit12));
    }
  }

  public static final class Standard3pChits extends ChitListImpl implements ChitList {
    public Standard3pChits() {
      super(one(chit2),
              one(chit3),
              copiesOf(chit4, 2),
              copiesOf(chit5, 2),
              copiesOf(chit6, 2),
              one(chit8),
              copiesOf(chit9, 2),
              copiesOf(chit10, 2),
              copiesOf(chit11, 2),
              one(chit12));
    }
  }

  public static final class SwapChits extends ChitListImpl implements ChitList {
    public SwapChits() {
      super(chit2, chit3, chit4, chit5, chit9, chit10, chit11);
    }
  }

  /** Sugar */
  public static ChitList newChits(Chit... chits) {
    return new ChitListImpl(chits);
  }
  public static ChitList copiesOf(Chit prototype, int amount) {
    MutableChitList result = new MutableChitListImpl();
    for (int i = 0; i < amount; i++)
      result.add(prototype.copy());
    return result.toImmutableModels();
  }
  public static ChitList one(Chit chit) {
    return new ChitListImpl(new Chit[] { chit.copy() });
  }

  public interface ChitList extends FullImmutableList<Chit, Integer> {
    public ChitList toImmutableModels();
    public ChitList copy();
    public ChitList only68();
    public ChitList no68();
  }

  public interface MutableChitList
          extends
          MutableModelList<Chit, ChitList, Integer>,
          ChitList {}

  public static class MutableChitListImpl
          extends
          FullMutableListImpl<Chit, ChitList, Integer>
          implements
          MutableChitList {
    @Override public ChitList toImmutableModels() {
      return new ChitListImpl(this);
    }
    @Override public ChitList copy() {
      return (ChitList) super.copy();
    }
    @Override public ChitList only68() {
      return toImmutableModels().only68();
    }
    @Override public ChitList no68() {
      return toImmutableModels().only68();
    }
  }

  public static class ChitListImpl
          extends
          AbstractFullImmutableList<Chit, ChitList, Integer>
          implements
          ChitList {
    public ChitListImpl() {
      super();
    }
    public ChitListImpl(Chit... items) {
      super(items);
    }
    public ChitListImpl(ChitList... chitLists) {
      super(chitLists);
    }
    @Override public ChitList copy() {
      // TODO Auto-generated method stub
      return (ChitList) super.copy();
    }
    /** Returns all 6/8 chits */
    @Override public ChitList only68() {
      MutableChitList result = new MutableChitListImpl();
      for (Chit chit : this)
        if (chit.isRed())
          result.add(chit);
      return result;
    }
    @Override public ChitList no68() {
      MutableChitList result = new MutableChitListImpl();
      for (Chit chit : this)
        if (!chit.isRed())
          result.add(chit);
      return result;
    }
    /** Counts amount of chits with given chitnumber */
    public int amount(int number) {
      int result = 0;
      for (Chit chit : this)
        if (chit.number() == number)
          result++;
      return result;
    }
    @Override public ChitList toImmutableModels() {
      return new ChitListImpl(this);
    }
  }
}
