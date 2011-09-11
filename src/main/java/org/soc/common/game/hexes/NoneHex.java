package org.soc.common.game.hexes;

import org.soc.common.game.Chit;
import org.soc.common.internationalization.I;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.gwt.client.images.R;

import com.google.gwt.resources.client.ImageResource;

/** Represents a hex mot rendered in the game, acting as design-time placeholder */
public class NoneHex extends AbstractHex {
  @Override public Icon icon() {
    return new IconImpl(R.icons().noneHex16(), R.icons().noneHex32(), R.icons().noneHex48());
  }
  @Override public String name() {
    return "NoneHex";
  }
  @Override public String getLocalizedName() {
    return I.get().constants().noneHex();
  }
  @Override public String getDescription() {
    return I.get().constants().noneHexDescription();
  }
  @Override public Hex copy() {
    return new NoneHex();
  }
  @Override public String getColor() {
    return "White";
  }
  @Override public boolean canBuildOnLand() {
    return false;
  }
  @Override public boolean canBuildOnSea() {
    return false;
  }
  @Override public boolean isPartOfGame() {
    return false;
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
    return R.images().noneHex();
  }
}
