package org.soc.common.game.hexes;

import org.soc.common.board.hexes.PortChangedEvent;
import org.soc.common.game.Chit;
import org.soc.common.game.Port;
import org.soc.common.game.Territory;
import org.soc.common.internationalization.I;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.gwt.client.images.R;

import com.google.gwt.resources.client.ImageResource;

public class SeaHex extends AbstractHex {
  private Port port;

  @Override public Icon icon() {
    return new IconImpl(R.icons().seaHex16(), R.icons().seaHex32(), R.icons().seaHex48());
  }
  @Override public String getLocalizedName() {
    return I.get().constants().seaHex();
  }
  @Override public String getDescription() {
    return I.get().constants().seaHexDescription();
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
  @Override public String getColor() {
    return "DarkBlue";
  }
  @Override public boolean canBuildOnLand() {
    return false;
  }
  @Override public boolean canBuildOnSea() {
    return true;
  }
  @Override public boolean isPartOfGame() {
    return true;
  }
  @Override public boolean isPiratePlaceable() {
    return true;
  }
  @Override public boolean isRobberPlaceable() {
    return false;
  }
  @Override public boolean canHaveChit() {
    return false;
  }
  @Override public boolean hasChit() {
    return false;
  }
  @Override public boolean producesResource() {
    return false;
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
}