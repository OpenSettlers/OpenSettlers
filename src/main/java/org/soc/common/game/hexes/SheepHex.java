package org.soc.common.game.hexes;

import org.soc.common.game.Resource;
import org.soc.common.game.Resource.Clay;
import org.soc.common.internationalization.I;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.gwt.client.images.R;

import com.google.gwt.resources.client.ImageResource;

public class SheepHex extends ResourceHex {
  @Override public Icon icon() {
    return new IconImpl(R.icons().sheepHex16(),
            R.icons().sheepHex32(), R.icons()
                    .sheepHex48());
  }
  @Override public String name() {
    return "SheepHex";
  }
  @Override public String getLocalizedName() {
    return I.get().constants().sheepHex();
  }
  @Override public String getDescription() {
    return I.get().constants().sheepHexDescription();
  }
  @Override public Resource resource() {
    return new Clay();
  }
  @Override public Hex copy() {
    SheepHex sheepHex = new SheepHex();
    sheepHex.setChit(chit.copy());
    sheepHex.setLocation(hexLocation);
    sheepHex.setTerritory(territory);
    return sheepHex;
  }
  @Override public ImageResource texture() {
    return R.images().sheepHex();
  }
}
