package org.soc.common.game;

import org.soc.common.core.GenericList.*;
import org.soc.common.core.GenericList.FullImmutableList.*;
import org.soc.common.core.GenericList.MutableList.*;
import org.soc.common.core.GenericList.MutableList.MutableModelList.*;
import org.soc.common.game.Resources.ResourceList;

import static org.soc.common.game.Port.Supported.*;

import static org.soc.common.game.Resources.*;

public class Ports {
  public static PortList twoToOnePorts = newPorts(timberPort, wheatPort, orePort, clayPort, sheepPort);
  public static PortList genericPorts = newPorts(threeToOnePort, fourToOnePort);
  public static PortList designPorts = new PortListImpl(twoToOnePorts, one(randomPort), genericPorts);
  public static PortList newStandard4Player = new PortListImpl(twoToOnePorts, newCopiesOf(threeToOnePort, 4));

  public static PortList one(Port port) {
    return new PortListImpl(new Port[] { port });
  }
  public static PortList newPorts(Port... ports) {
    return new PortListImpl(ports);
  }
  public static PortList newCopiesOf(Port prototype, int amount) {
    MutablePortList result = new MutablePortListImpl();
    for (int i = 0; i < amount; i++)
      result.add(prototype.copy());
    return result.toImmutable();
  }

  public interface PortList extends FullImmutableList<Port, Integer> {
    public Port bestPortForResource(Resource resourceType, boolean ignoreFourToOne);
    public int amountNeededToTrade(Resource resourceType);
    public int amountGold(ResourceList resources);
    //public PortList tradebleResources();
  }

  public interface MutablePortList
          extends
          MutableModelList<Port, PortList, Integer>,
          PortList {
    public PortList toImmutable();
  }

  public static class MutablePortListImpl
          extends
          FullMutableListImpl<Port, PortList, Integer>
          implements
          MutablePortList {
    public MutablePortListImpl() {
      super();
    }
    public MutablePortListImpl(PortList newStandard4Player) {
      super(newStandard4Player);
    }
    @Override public PortList toImmutable() {
      return new PortListImpl(this);
    }
    @Override public Port bestPortForResource(Resource resourceType, boolean ignoreFourToOne) {
      return toImmutable().bestPortForResource(resourceType, ignoreFourToOne);
    }
    @Override public int amountNeededToTrade(Resource resourceType) {
      return toImmutable().amountNeededToTrade(resourceType);
    }
    @Override public int amountGold(ResourceList resources) {
      return toImmutable().amountGold(resources);
    }
  }

  public static class PortListImpl
          extends
          AbstractFullImmutableList<Port, PortList, Integer>
          implements
          PortList {
    public PortListImpl(FullImmutableList<Port, Integer>... lists) {
      super(lists);
    }
    public PortListImpl() {/** Default serializable constructor */
    }
    public PortListImpl(Port[] ports) {
      super(ports);
    }
    /** Returns amount of gold possibly be gained by given resources */
    @Override public int amountGold(ResourceList resources) {
      int amountGold = 0;
      // Calculate amount of gold per possible resource type in which
      // we are allowed to trade.
      // For standard, this will be the 5 basic resources
      // For CitiesKnights, this will be standard + coin/paper/cloth
      for (Resource type : tradableResources) {
        // get a list of resources of given type
        ResourceList resourcesOfType = resources.ofType(type);
        // Amount of gold we can get with current port
        int portGoldAmount = 0;
        // Return the highest amount of gold given a set of ports
        for (Port port : this) {
          // Get amount of gold we can get with given resourcetype and
          // port
          if (port.canTrade(type)) {
            int possibleGold = port.divide(resourcesOfType, type);
            // When a port tops production of any previous port, set it
            if (possibleGold > portGoldAmount) {
              portGoldAmount = possibleGold;
            }
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
    /** Returns amount of resources of given type needed to make a trade */
    @Override public int amountNeededToTrade(Resource resourceType) {
      return bestPortForResource(resourceType, false).inAmount();
    }
    /** Returns best port to trade with given the resource type. When this port is a 4:1 port, it
     * ignores this port and returns null */
    @Override public Port bestPortForResource(Resource resourceType, boolean ignoreFourToOne) {
      Port result = null;
      int amountNeeded = 6;
      for (Port port : this) {
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
  }
}
