package org.soc.common.game.hexes;

import org.soc.common.game.*;
import org.soc.gwt.client.images.*;

import com.google.gwt.resources.client.*;

public class DesertHex extends AbstractHex {
  public DesertHex() {
    super();
    color = "DarkKhaki";
    canBuldOnLand = true;
    canBuildOnSea = false;
    isPartoOfGame = true;
    isPiratePlaceable = false;
    isRobberPlaceable = true;
    canHaveChit = false;
    hasChit = false;
    producesResource = false;
  }
  @Override public Hex copy() {
    DesertHex result = new DesertHex();
    result.setTerritory(territory);
    return result;
  }
  @Override public Chit chit() {
    return null;
  }
  @Override public Hex setChit(Chit chit) {
    return this;
  }
  @Override public ImageResource texture() {
    return R.images().desertHex();
  }
  @Override public boolean hasChit() {
    return false;
  }
}
