package org.soc.common.game;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.soc.common.game.Territory.TerritoryImpl;
import org.soc.common.game.TerritoryListChangedEvent.TerritoryListChangedHandler;

public class TerritoryListTest {
  private boolean eventFiredWhenAdding = false;
  private boolean eventFiredWhenRemoving = false;

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
  @Test public void testFiresEventsWhenAdding() {
    TerritoryList territories = new TerritoryList();
    final Territory territory = new TerritoryImpl().setMainland(false);
    territories.addTerritoryListChangedHandler(new TerritoryListChangedHandler() {
      @Override public void onTerritoryListChanged(TerritoryListChangedEvent event) {
        assertTrue("Expected added Territory to be the just added territory", territory
                .equals(event.getAddedTerritory()));
        eventFiredWhenAdding = true;
      }
    });
    territories.add(territory);
    assertTrue("Expected event to be fired after adding a territory", eventFiredWhenAdding);
  }
  @Test public void testFiresEventsWhenRemoving() {
    TerritoryList territories = new TerritoryList();
    final Territory territory = new TerritoryImpl().setMainland(false);
    territories.add(territory);
    territories.addTerritoryListChangedHandler(new TerritoryListChangedHandler() {
      @Override public void onTerritoryListChanged(TerritoryListChangedEvent event) {
        assertTrue("Expected added Territory to be the just removed territory", territory
                .equals(event.getRemovedTerritory()));
        eventFiredWhenRemoving = true;
      }
    });
    territories.remove(territory);
    assertTrue("Expected event to be fired after adding a territory", eventFiredWhenRemoving);
  }
}
