package org.soc.common.game;

import org.soc.common.core.GenericList.*;
import org.soc.common.core.GenericList.FullImmutableList.*;
import org.soc.common.core.GenericList.MutableList.*;
import org.soc.common.core.GenericList.MutableList.MutableModelList.*;

import static org.soc.common.game.Territory.Supported.*;

public class Territories {
  public interface Supported {
    public static TerritoryList standardWithMainland = new StandardWithMainland();
  }

  public interface TerritoryList extends FullImmutableList<Territory, Integer> {
    public boolean containsMainland();
  }

  public final static class StandardWithMainland extends TerritoryListImpl {
    public StandardWithMainland() {
      super(new Territory[] { mainland });
    }
  }

  public interface MutableTerritoryList
          extends
          MutableModelList<Territory, TerritoryList, Integer>,
          TerritoryList {
    public TerritoryList toImmutableModels();
  }

  public static class MutableTerritoryListImpl
          extends
          FullMutableListImpl<Territory, TerritoryList, Integer>
          implements
          MutableTerritoryList {
    public MutableTerritoryListImpl(TerritoryList standardWithMainland) {
      super(standardWithMainland);
    }
    @Override public boolean containsMainland() {
      return toImmutableModels().containsMainland();
    }
    @Override public TerritoryList toImmutableModels() {
      return new TerritoryListImpl(this);
    }
    @Override public void add(Territory item) {
      if (containsMainland() && item.isMainland())
        throw new RuntimeException("TerritoryLists can only cnotain one mainland");
      super.add(item);
    }
  }

  public static class TerritoryListImpl
          extends
          AbstractFullImmutableList<Territory, TerritoryList, Integer>
          implements TerritoryList {
    public TerritoryListImpl(Territory[] items) {
      super(items);
    }
    public TerritoryListImpl(MutableTerritoryListImpl mutableTerritoryListImpl) {
      super(mutableTerritoryListImpl);
    }
    @Override public boolean containsMainland() {
      for (Territory territory : items)
        if (territory.isMainland())
          return true;
      return false;
    }
  }
}
