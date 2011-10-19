package org.soc.common.game.hexes;

import org.soc.common.game.*;
import org.soc.gwt.client.images.*;

import com.google.gwt.resources.client.*;

/** Represents a hex mot rendered in the game, acting as design-time placeholder */
public class NoneHex extends AbstractHex {
  public NoneHex() {
    super();
    color = "White";
    canBuldOnLand = false;
    canBuildOnSea = false;
    isPartoOfGame = false;
    isPiratePlaceable = false;
    isRobberPlaceable = false;
    canHaveChit = false;
    hasChit = false;
    producesResource = false;
  }
  @Override public Hex copy() {
    return new NoneHex();
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
    return R.images().noneHex();
  }
}
