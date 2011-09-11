package org.soc.common.game.hexes;

import static org.soc.common.internationalization.I.constants;

import org.soc.common.game.Resource;
import org.soc.common.game.Resource.Wheat;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.gwt.client.images.R;

import com.google.gwt.resources.client.ImageResource;

public class WheatHex extends ResourceHex {
  @Override public Icon icon() {
    return new IconImpl(R.icons().wheatHex16(), R.icons().wheatHex32(), R.icons().wheatHex48());
  }
  @Override public String name() {
    return "WheatHex";
  }
  @Override public String getLocalizedName() {
    return constants.wheatHex();
  }
  @Override public String getDescription() {
    return constants.wheatHexDescription();
  }
  @Override public Resource resource() {
    return new Wheat();
  }
  @Override public Hex copy() {
    WheatHex wheatHex = new WheatHex();
    wheatHex.setChit(chit.copy());
    wheatHex.setLocation(hexLocation);
    wheatHex.setTerritory(territory);
    return wheatHex;
  }
  @Override public ImageResource texture() {
    return R.images().wheatHex();
  }
}
