package org.soc.common.game.hexes;

import org.soc.common.core.property.Properties.Description;
import org.soc.common.core.property.Properties.Name;
import org.soc.common.game.*;
import org.soc.common.internationalization.*;
import org.soc.common.views.meta.*;
import org.soc.gwt.client.images.*;

import com.google.gwt.resources.client.*;

/** Represents a hex mot rendered in the game, acting as design-time placeholder */
public class NoneHex extends AbstractHex {
  @Override public Icon icon() {
    return new IconImpl(R.icons().noneHex16(), R.icons().noneHex32(), R.icons().noneHex48());
  }
  @Override public Name name() {
    return new Name.Impl(I.get().constants().noneHex());
  }
  @Override public Description description() {
    return new Description.Impl(I.get().constants().noneHexDescription());
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
