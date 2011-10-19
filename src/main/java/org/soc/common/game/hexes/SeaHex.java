package org.soc.common.game.hexes;

import org.soc.common.game.*;
import org.soc.gwt.client.images.*;

import com.google.gwt.resources.client.*;

public class SeaHex extends AbstractHex {
  private Port port;

  public SeaHex() {
    super();
    color = "DarkBlue";
    canBuldOnLand = false;
    canBuildOnSea = true;
    isPartoOfGame = true;
    isPiratePlaceable = true;
    isRobberPlaceable = false;
    canHaveChit = false;
    hasChit = false;
    producesResource = false;
  }
  /** Ignored, sea hexes do not have a territory */
  @Override public Hex setTerritory(Territory t) {
    return this;
  }
  @Override public Port port() {
    return port;
  }
  @Override public AbstractHex setPort(Port p) {
    this.port = p;
    eventBus.fireEvent(new PortChangedEvent(port));
    return this;
  }
  /* Copies this sea hex without the port */
  @Override public Hex copy() {
    return new SeaHex()
            .setLocation(hexLocation);
  }
  @Override public Chit chit() {
    return null;
  }
  /** Does nothing, SeaHexes do not support chits */
  @Override public Hex setChit(Chit chit) {
    return this;
  }
  @Override public boolean canHavePort() {
    return port != null;
  }
  @Override public ImageResource texture() {
    return R.images().seaHex();
  }
  @Override public boolean hasChit() {
    return false;
  }
}