package org.soc.common.game.hexes;

import org.soc.common.game.*;
import org.soc.common.game.Resource.Wheat;
import org.soc.gwt.client.images.*;

import com.google.gwt.resources.client.*;

public class WheatHex extends ResourceHex {
  @Override public Resource resource() {
    return new Wheat();
  }
  @Override public Hex copy() {
    WheatHex wheatHex = new WheatHex();
    wheatHex.setChit(copyChit());
    wheatHex.setLocation(hexLocation);
    wheatHex.setTerritory(territory);
    return wheatHex;
  }
  @Override public ImageResource texture() {
    return R.images().wheatHex();
  }
}
