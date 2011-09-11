package org.soc.common.game.hexes;

import org.soc.common.game.Resource;
import org.soc.common.game.Resource.Gold;
import org.soc.common.internationalization.I;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.gwt.client.images.R;

import com.google.gwt.resources.client.ImageResource;

public class GoldHex extends ResourceHex {
  @Override public Icon icon() {
    return new IconImpl(R.icons().goldHex16(), R.icons().goldHex32(), R.icons().goldHex48());
  }
  @Override public String name() {
    return "GoldHex";
  }
  @Override public String getLocalizedName() {
    return I.get().constants().goldHex();
  }
  @Override public String getDescription() {
    return I.get().constants().goldHexDescription();
  }
  @Override public Resource resource() {
    return new Gold();
  }
  @Override public Hex copy() {
    return (GoldHex) new GoldHex()
            .setChit(chit.copy())
            .setLocation(hexLocation)
            .setTerritory(territory);
  }
  @Override public ImageResource texture() {
    return R.images().goldHex();
  }
}