package org.soc.common.game.hexes;

import org.soc.common.game.Chit;
import org.soc.common.internationalization.I;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.gwt.client.images.R;

import com.google.gwt.resources.client.ImageResource;

public class DesertHex extends AbstractHex {
  @Override public Icon icon() {
    return new IconImpl(R.icons().desertHex16(), R.icons().desertHex32(), R.icons().desertHex48());
  }
  @Override public String getLocalizedName() {
    return I.get().constants().desertHex();
  }
  @Override public String getDescription() {
    return I.get().constants().desertHexDescription();
  }
  @Override public Hex copy() {
    DesertHex result = new DesertHex();
    result.setTerritory(territory);
    return result;
  }
  @Override public String getColor() {
    return "DarkKhaki";
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
    return R.images().desertHex();
  }
}
