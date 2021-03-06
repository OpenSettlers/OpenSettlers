package org.soc.common.game.hexes;

import org.soc.common.game.Chit;
import org.soc.common.utils.Util;

public abstract class ResourceHex extends AbstractHex {
  private static final long serialVersionUID = 7086634587082441341L;
  protected Chit chit;

  public Chit chit() {
    return chit;
  }
  public Hex setChit(Chit c) {
    this.chit = c;
    eventBus.fireEvent(new ChitChangedEvent(c));
    return this;
  }
  @Override public String getColor() {
    return resource().color();
  }
  @Override public String getName() {
    return Util.shortName(this.getClass());
  }
  @Override public boolean canBuildOnLand() {
    return true;
  }
  @Override public boolean canBuildOnSea() {
    return false;
  }
  @Override public boolean isPartOfGame() {
    return true;
  }
  @Override public boolean isPiratePlaceable() {
    return false;
  }
  @Override public boolean isRobberPlaceable() {
    return true;
  }
  @Override public boolean canHaveChit() {
    return true;
  }
  @Override public boolean hasChit() {
    return chit != null;
  }
  @Override public boolean producesResource() {
    return true;
  }
}
