package org.soc.common.game.hexes;

import org.soc.common.game.Resource;
import org.soc.common.game.Resource.Clay;
import org.soc.common.internationalization.I;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.gwt.client.images.R;

import com.google.gwt.resources.client.ImageResource;

public class ClayHex extends ResourceHex {
  @Override public Icon icon() {
    return new IconImpl(R.icons().clayHex16(), R.icons().clayHex32(), R.icons().clayHex48());
  }
  @Override public String getLocalizedName() {
    return I.get().constants().clayHex();
  }
  @Override public String getDescription() {
    return I.get().constants().clayHexDescription();
  }
  @Override public Resource resource() {
    return new Clay();
  }
  @Override public Hex copy() {
    return new ClayHex()
            .setChit(chit.copy())
            .setLocation(hexLocation)
            .setTerritory(territory);
  }
  @Override public ImageResource texture() {
    return R.images().clayHex();
  }
}
