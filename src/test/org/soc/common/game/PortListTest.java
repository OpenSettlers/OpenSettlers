package org.soc.common.game;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.soc.common.board.PortListChangedEvent;
import org.soc.common.board.PortListChangedEvent.PortListChangedHandler;
import org.soc.common.game.Port.ThreeToOnePort;
import org.soc.common.game.Port.WheatPort;
import org.soc.common.game.Resource.Wheat;

public class PortListTest {
  boolean hasFiredAddEvent = false;
  boolean hasFiredremoveEvent = false;

  @Test public void testCanTrade() {
    PortList ports = PortList.newStandard4Player();
    Wheat wheat = new Wheat();
    Port bestPort = ports.bestPortForResource(wheat, true);
    assertTrue("Expected best port to be a WheatPort",
            bestPort instanceof WheatPort);
    ports.remove(bestPort);
    bestPort = ports.bestPortForResource(wheat, true);
    assertTrue("Expected best port to be a 3:1 port", bestPort instanceof ThreeToOnePort);
  }
  @Test public void testAmountGold() {
    ResourceList basicResources = ResourceList.newFiveBasicResources();
    PortList ports = PortList.newStandard4Player();
    assertTrue("Expected zero gold", ports.amountGold(basicResources) == 0);
    Wheat wheat = new Wheat();
    basicResources.add(wheat);
    assertTrue("Expected one gold", ports.amountGold(basicResources) == 1);
    basicResources.remove(wheat);
    basicResources.addList(ResourceList.newFiveBasicResources());
    assertTrue("Expected five gold", ports.amountGold(basicResources) == 5);
    Port portToRemove = null;
    for (Port port : ports)
      if (port instanceof WheatPort)
        portToRemove = port;
    ports.remove(portToRemove);
    assertTrue("Expected four gold", ports.amountGold(basicResources) == 4);
    basicResources.addList(ResourceList.newFiveBasicResources());
    assertTrue("Expected five gold", ports.amountGold(basicResources) == 5);
  }
  @Test public void testFiresEventsWhenAdding() {
    PortList ports = new PortList();
    final WheatPort wheatPort = new WheatPort();
    ports.addPortListChangedHandler(new PortListChangedHandler() {
      @Override public void onPortListChanged(PortListChangedEvent event) {
        hasFiredAddEvent = true;
        assertTrue(event.getAddedPort().equals(wheatPort));
      }
    });
    ports.add(wheatPort);
    assertTrue("Expected add event to be fired", hasFiredAddEvent);
  }
  @Test public void testFiresEventsWhenRemoving() {
    PortList ports = new PortList();
    final WheatPort wheatPort = new WheatPort();
    ports.add(wheatPort);
    ports.addPortListChangedHandler(new PortListChangedHandler() {
      @Override public void onPortListChanged(PortListChangedEvent event) {
        hasFiredremoveEvent = true;
        assertTrue(event.getRemovedPort().equals(wheatPort));
      }
    });
    ports.remove(wheatPort);
    assertTrue("Expected remove event to be fired", hasFiredremoveEvent);
  }
}
