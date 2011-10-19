package org.soc.common.game.hexes;

import org.soc.common.game.*;
import org.soc.gwt.client.images.*;

import com.google.gwt.resources.client.*;

/** Represents a designtime hex to be replaced by a LayoutStrategy. */
public class RandomHex extends AbstractHex {
  public RandomHex() {
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
    return new RandomHex();
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
    return R.images().randomHex();
  }
}