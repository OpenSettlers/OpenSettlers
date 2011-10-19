package org.soc.common.game.hexes;

import org.soc.common.game.*;
import org.soc.common.game.Resource.Gold;
import org.soc.common.game.board.*;
import org.soc.gwt.client.images.*;

import com.google.gwt.resources.client.*;

public class GoldHex extends ResourceHex {
  @Override public Resource resource() {
    return new Gold();
  }
  @Override public Hex copy() {
    return new GoldHex()
            .setChit(copyChit())
            .setLocation(hexLocation)
            .setTerritory(territory);
  }
  @Override public ImageResource texture() {
    return R.images().goldHex();
  }
  @Override public GoldHex setTerritory(Territory territory) {
    this.territory = territory;
    return this;
  }
  @Override public GoldHex setChit(Chit chit) {
    this.chit = chit;
    return this;
  }
  @Override public GoldHex setLocation(HexLocation location) {
    this.hexLocation = location;
    return this;
  }
}