package org.soc.common.game;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.soc.common.game.DevelopmentCard.VictoryPoint;
import org.soc.common.game.VictoryPointsChangedEvent.VictoryPointsChangedHandler;
import org.soc.common.game.pieces.City;
import org.soc.common.game.pieces.Town;

public class VictoryPointsListTest {
  private boolean eventFiredWhenAdding = false;
  private boolean eventFiredWhenRemoving = false;

  @Test public void testVictoryPointsCount() {
    VictoryPointsList victoryPointsList = createListWithTownCityDevVp();
    assertTrue("Expected 4 victory points", // City=2, town=1, vp=1 
            victoryPointsList.total() == 4);
  }
  private VictoryPointsList createListWithTownCityDevVp() {
    City city = new City();
    Town town = new Town();
    VictoryPoint victoryPoint = new VictoryPoint();
    VictoryPointsList victoryPointsList = new VictoryPointsList();
    victoryPointsList.add(city);
    victoryPointsList.add(town);
    victoryPointsList.add(victoryPoint);
    return victoryPointsList;
  }
  @Test public void testFiresAddEvent() {
    VictoryPointsList victoryPointsList = new VictoryPointsList();
    final City city = new City();
    victoryPointsList.addVictoryPointsChangedListener(new VictoryPointsChangedHandler() {
      @Override public void onVictoryPointsChanged(VictoryPointsChangedEvent event) {
        eventFiredWhenAdding = true;
        assertTrue("Expected city to be added", city.equals(event.getAddedPoint()));
      }
    });
    victoryPointsList.add(city);
    assertTrue("Expected event to be fired when adding city", eventFiredWhenAdding);
  }
  @Test public void testFiresRemoveEvent() {
    VictoryPointsList victoryPointsList = new VictoryPointsList();
    final City city = new City();
    victoryPointsList.add(city);
    victoryPointsList.addVictoryPointsChangedListener(new VictoryPointsChangedHandler() {
      @Override public void onVictoryPointsChanged(VictoryPointsChangedEvent event) {
        eventFiredWhenRemoving = true;
        assertTrue("Expected city to be removed", city.equals(event.getRemovedPoint()));
      }
    });
    victoryPointsList.remove(city);
    assertTrue("Expected event to be fired when removing city", eventFiredWhenRemoving);
  }
  @Test public void testTypeFilter() {
    VictoryPointsList victoryPointsList = createListWithTownCityDevVp();
    assertTrue("Expected one VictoryPointItem of type city",
            victoryPointsList.ofType(new City()).size() == 1);
    assertTrue("Expected one VictoryPointItem of type town",
            victoryPointsList.ofType(new Town()).size() == 1);
    assertTrue("Expected one VictoryPointItem of type victorypoint development card",
            victoryPointsList.ofType(new VictoryPoint()).size() == 1);
  }
}
