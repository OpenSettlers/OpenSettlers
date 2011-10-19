package org.soc.common.game.hexes;

import org.soc.common.game.*;
import org.soc.common.game.Resource.Clay;
import org.soc.gwt.client.images.*;

import com.google.gwt.resources.client.*;

public class ClayHex extends ResourceHex {
  @Override public Resource resource() {
    return new Clay();
  }
  @Override public Hex copy() {
    return new ClayHex()
            .setChit(copyChit())
            .setLocation(hexLocation)
            .setTerritory(territory);
  }
  @Override public ImageResource texture() {
    return R.images().clayHex();
  }
}
