package org.soc.common.game.hexes;

import org.soc.common.game.*;
import org.soc.common.game.Resource.Ore;
import org.soc.gwt.client.images.*;

import com.google.gwt.resources.client.*;

public class OreHex extends ResourceHex {
  @Override public Resource resource() {
    return new Ore();
  }
  @Override public Hex copy() {
    OreHex oreHex = new OreHex();
    oreHex.setChit(copyChit());
    oreHex.setLocation(hexLocation);
    oreHex.setTerritory(territory);
    return oreHex;
  }
  @Override public ImageResource texture() {
    return R.images().oreHex();
  }
}
