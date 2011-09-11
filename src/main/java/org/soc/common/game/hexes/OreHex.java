package org.soc.common.game.hexes;

import org.soc.common.game.Resource;
import org.soc.common.game.Resource.Ore;
import org.soc.common.internationalization.I;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.gwt.client.images.R;

import com.google.gwt.resources.client.ImageResource;

public class OreHex extends ResourceHex {
  @Override public Icon icon() {
    return new IconImpl(R.icons().oreHex16(), R.icons().oreHex32(), R.icons().oreHex48());
  }
  @Override public String name() {
    return "OreHex";
  }
  @Override public String getLocalizedName() {
    return I.get().constants().oreHex();
  }
  @Override public String getDescription() {
    return I.get().constants().oreHexDescription();
  }
  @Override public Resource resource() {
    return new Ore();
  }
  @Override public Hex copy() {
    OreHex oreHex = new OreHex();
    oreHex.setChit(chit.copy());
    oreHex.setLocation(hexLocation);
    oreHex.setTerritory(territory);
    return oreHex;
  }
  @Override public ImageResource texture() {
    return R.images().oreHex();
  }
}
