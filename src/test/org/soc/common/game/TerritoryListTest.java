package org.soc.common.game;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.soc.common.game.Territory.TerritoryImpl;

public class TerritoryListTest {
  @Test public void testMaximumOneMainland() {
    TerritoryList territories = TerritoryList.standardWithMainland();
    boolean isExceptionThrown = false;
    try {
      territories.add(new TerritoryImpl().setMainland(true));
    } catch (Exception ex) {
      isExceptionThrown = true;
    }
    assertTrue("Expected exception to be thrown when adding mainland to TerritoryList with mainland alreayd in it", isExceptionThrown);
  }
}
