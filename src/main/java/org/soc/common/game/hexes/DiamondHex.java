package org.soc.common.game.hexes;

import org.soc.common.game.Resource;
import org.soc.common.game.Resource.Diamond;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.gwt.client.images.R;

import com.google.gwt.resources.client.ImageResource;

public class DiamondHex extends ResourceHex {
  @Override public Icon icon() {
    return new IconImpl(
            R.icons().diamondHex16(),
            R.icons().diamondHex32(),
            R.icons().diamondHex48());
  }
  @Override public String name() {
    return "DiamondHex";
  }
  @Override public String getLocalizedName() {
    return null;
  }
  @Override public String getDescription() {
    return null;
  }
  @Override public Resource resource() {
    return new Diamond();
  }
  @Override public Hex copy() {
    DiamondHex diamondHex = new DiamondHex();
    diamondHex.setChit(chit.copy());
    diamondHex.setLocation(hexLocation);
    diamondHex.setTerritory(territory);
    return diamondHex;
  }
  @Override public ImageResource texture() {
    return R.images().diamondHex();
  }
}