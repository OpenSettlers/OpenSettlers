package org.soc.common.game.hexes;

import org.soc.common.core.property.Properties.Description;
import org.soc.common.core.property.Properties.Name;
import org.soc.common.game.*;
import org.soc.common.internationalization.*;
import org.soc.common.views.meta.*;
import org.soc.gwt.client.images.*;

import com.google.gwt.resources.client.*;

/** Represents a designtime hex to be replaced by a LayoutStrategy. */
public class RandomHex extends AbstractHex {
  @Override public Icon icon() {
    return new IconImpl(R.icons().randomHex16(), R.icons().randomHex32(), R.icons().randomHex48());
  }
  @Override public Name name() {
    return new Name.Impl(I.get().constants().randomHex());
  }
  @Override public Description description() {
    return new Description.Impl(I.get().constants().randomHexDescription());
  }
  @Override public Hex copy() {
    return new RandomHex();
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
    return R.images().randomHex();
  }
}