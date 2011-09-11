package org.soc.common.game.hexes;

import org.soc.common.game.Resource;
import org.soc.common.game.Resource.Timber;
import org.soc.common.internationalization.I;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.gwt.client.images.R;

import com.google.gwt.resources.client.ImageResource;

public class TimberHex extends ResourceHex {
  @Override public Icon icon() {
    return new IconImpl(
            R.icons().timberHex16(),
            R.icons().timberHex32(),
            R.icons().timberHex48());
  }
  @Override public String getLocalizedName() {
    return I.get().constants().timberHex();
  }
  @Override public String getDescription() {
    return I.get().constants().timberHexDescription();
  }
  @Override public Resource resource() {
    return new Timber();
  }
  @Override public Hex copy() {
    TimberHex timberHex = new TimberHex();
    timberHex.setChit(chit.copy());
    timberHex.setLocation(hexLocation);
    timberHex.setTerritory(territory);
    return timberHex;
  }
  @Override public ImageResource texture() {
    return R.images().timberHex();
  }
}
