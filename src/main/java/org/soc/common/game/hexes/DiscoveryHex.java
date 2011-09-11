package org.soc.common.game.hexes;

import org.soc.common.game.Chit;
import org.soc.common.internationalization.I;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.gwt.client.images.R;

import com.google.gwt.resources.client.ImageResource;

public class DiscoveryHex extends AbstractHex {
  @Override public Icon icon() {
    return new IconImpl(R.icons().discoveryHex32(),
            null, null, null);
  }
  @Override public String name() {
    return "DiscoveryHex";
  }
  @Override public String getLocalizedName() {
    return I.get().constants().discoveryHex();
  }
  @Override public String getDescription() {
    return I.get().constants().discoveryHexDescription();
  }
  @Override public Hex copy() {
    DiscoveryHex result = new DiscoveryHex();
    result.setTerritory(territory);
    return result;
  }
  @Override public String getColor() {
    return "White";
  }
  @Override public boolean canBuildOnLand() {
    return false;
  }
  /** Pieces on a sea side can't build */
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
  @Override public Hex setChit(Chit chit) {
    return this;
  }
  @Override public ImageResource texture() {
    return R.images().discoveryHex();
  }
}