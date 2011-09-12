package org.soc.common.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.soc.common.board.PortListChangedEvent;
import org.soc.common.board.PortListChangedEvent.HasPortListChangedHandlers;
import org.soc.common.board.PortListChangedEvent.PortListChangedHandler;
import org.soc.common.game.Port.ClayPort;
import org.soc.common.game.Port.OrePort;
import org.soc.common.game.Port.SheepPort;
import org.soc.common.game.Port.ThreeToOnePort;
import org.soc.common.game.Port.TimberPort;
import org.soc.common.game.Port.WheatPort;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import com.gwtplatform.dispatch.annotation.GenEvent;
import com.gwtplatform.dispatch.annotation.Order;

public class PortList implements Iterable<Port>, Serializable, HasPortListChangedHandlers {
  @GenEvent public class PortListChanged {
    @Order(0) Port addedPort;
    @Order(1) Port removedPort;
  }

  private transient ResourceList tradeableResourceTypes = ResourceList
          .tradeableResources();
  private transient EventBus eventBus = new SimpleEventBus();
  private List<Port> ports = new ArrayList<Port>();

  /** Returns a new list of ports suitable for a standard 4p game */
  public static PortList newMain4p() {
    PortList result = new PortList();
    for (int i = 0; i < 4; i++)
      result.add(new ThreeToOnePort());
    result.add(new TimberPort());
    result.add(new WheatPort());
    result.add(new OrePort());
    result.add(new ClayPort());
    result.add(new SheepPort());
    return result;
  }
  /** Returns amount of gold possibly be gained by given resources */
  public int amountGold(ResourceList resources) {
    int amountGold = 0;
    // Calculate amount of gold per possible resource type in which
    // we are allowed to trade.
    // For standard, this will be the 5 basic resources
    // For CitiesKnights, this will be standard + coin/paper/cloth
    for (Resource type : tradeableResourceTypes) {
      // get a list of resources of given type
      ResourceList resourcesOfType = resources.ofType(type);
      // Amount of gold we can get with current port
      int portGoldAmount = 0;
      // Return the highest amount of gold given a set of ports
      for (Port port : ports) {
        // Get amount of gold we can get with given resourcetype and
        // port
        int possibleGold = port.divide(resourcesOfType, type);
        // When a port tops production of any previous port, set it
        if (possibleGold > portGoldAmount) {
          portGoldAmount = possibleGold;
        }
      }
      // Add the gold amount to the total amount of gold
      amountGold += portGoldAmount;
    }
    // Tada! Here we have the maximum amount of gold we can trade for,
    // given:
    // -a set of ports
    // -a set of resources
    // -a set of resources allowed to trade with
    return amountGold;
  }
  public PortList() {/** Default serializable constructor */
  }
  /* Creates an empty PortList */
  public PortList(boolean b) {}
  public void add(Port port) {
    ports.add(port);
    eventBus.fireEvent(new PortListChangedEvent(port, null));
  }
  public void remove(Port port) {
    ports.remove(port);
    eventBus.fireEvent(new PortListChangedEvent(null, port));
  }
  /** Returns amount of resources of given type needed to make a trade */
  public int amountNeededToTrade(Resource resourceType) {
    return getBestPortForResource(resourceType, false).inAmount();
  }
  @Override public Iterator<Port> iterator() {
    return ports.iterator();
  }
  public HandlerRegistration addPortListChangedHandler(PortListChangedHandler handler) {
    return eventBus.addHandler(PortListChangedEvent.getType(), handler);
  }
  /* Returns best port to trade with given the resource type. When this port is a 4:1 port, it
   * ignores this port and returns null */
  public Port getBestPortForResource(Resource resourceType, boolean ignoreFourToOne) {
    Port result = null;
    int amountNeeded = 6;
    for (Port port : ports) {
      // Check if we can use the port to trade the given resource type
      // with
      if (port.canTrade(resourceType)) {
        // When a port tops production of any previous port, set it
        if (port.inAmount() < amountNeeded) {
          amountNeeded = port.inAmount();
          result = port;
        }
      }
    }
    if (amountNeeded == 4 && ignoreFourToOne) {
      result = null;
    }
    return result;
  }
  public int size() {
    return ports.size();
  }
  public Port get(int index) {
    return ports.get(index);
  }
  @Override public void fireEvent(GwtEvent<?> event) {
    eventBus.fireEvent(event);
  }
}
