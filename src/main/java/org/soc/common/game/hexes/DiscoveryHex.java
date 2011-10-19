package org.soc.common.game.hexes;

import org.soc.common.game.*;
import org.soc.gwt.client.images.*;

import com.google.gwt.resources.client.*;

public class DiscoveryHex extends AbstractHex {
  public DiscoveryHex() {
    super();
    color = "White";
    canBuldOnLand = false;
    canBuildOnSea = false;
    isPartoOfGame = true;
    isPiratePlaceable = false;
    isRobberPlaceable = false;
    canHaveChit = false;
    hasChit = false;
    producesResource = false;
  }
  @Override public Hex copy() {
    DiscoveryHex result = new DiscoveryHex();
    result.setTerritory(territory);
    return result;
  }
  @Override public boolean hasChit() {
    return false;
  }
  @Override public Chit chit() {
    return null;
  }
  @Override public Hex setChit(Chit chit) {
    return this;
  }
  @Override public ImageResource texture() {
    return R.images().discoveryHex();
  }
}