package org.soc.common.game.hexes;

import org.soc.common.game.*;
import org.soc.common.game.Resource.Timber;
import org.soc.gwt.client.images.*;

import com.google.gwt.resources.client.*;

public class TimberHex extends ResourceHex {
  @Override public Resource resource() {
    return new Timber();
  }
  @Override public Hex copy() {
    TimberHex timberHex = new TimberHex();
    timberHex.setChit(copyChit());
    timberHex.setLocation(hexLocation);
    timberHex.setTerritory(territory);
    return timberHex;
  }
  @Override public ImageResource texture() {
    return R.images().timberHex();
  }
}
