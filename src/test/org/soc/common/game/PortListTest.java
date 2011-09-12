package org.soc.common.game;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.soc.common.game.Port.ThreeToOnePort;
import org.soc.common.game.Port.WheatPort;
import org.soc.common.game.Resource.Wheat;

public class PortListTest {
  @Test public void test() {
    PortList ports = PortList.newMain4p();
    Wheat wheat = new Wheat();
    Port bestPort = ports.bestPortForResource(wheat, true);
    assertTrue(bestPort instanceof WheatPort);
    ports.remove(bestPort);
    bestPort = ports.bestPortForResource(wheat, true);
    assertTrue(bestPort instanceof ThreeToOnePort);
  }
}
