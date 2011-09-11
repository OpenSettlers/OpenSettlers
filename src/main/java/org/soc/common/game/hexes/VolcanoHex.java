package org.soc.common.game.hexes;

import org.soc.common.game.Resource;
import org.soc.common.game.Resource.Gold;
import org.soc.common.internationalization.I;
import org.soc.common.utils.Util;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.gwt.client.images.R;

import com.google.gwt.resources.client.ImageResource;

public class VolcanoHex extends ResourceHex {
  private Resource resource = new Gold();

  @Override public Icon icon() {
    return new IconImpl(null, null, R.icons()
            .volcanoHex32(), null);
  }
  @Override public String getLocalizedName() {
    return I.get().constants().volcanoHex();
  }
  @Override public String getDescription() {
    return I.get().constants().volcanoHexDescription();
  }
  @Override public Resource resource() {
    return resource;
  }
  @Override public String getColor() {
    return "DarkRed";
  }
  @Override public String getName() {
    return Util.shortName(this.getClass());
  }
  @Override public Hex copy() {
    VolcanoHex result = new VolcanoHex();
    result.setTerritory(territory);
    return result;
  }
  @Override public boolean canHaveChit() {
    return true;
  }
  @Override public boolean hasChit() {
    return chit != null;
  }
  @Override public boolean producesResource() {
    return true;
  }
  @Override public ImageResource texture() {
    return R.images().volcanoHex();
  }
}