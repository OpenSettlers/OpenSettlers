package org.soc.common.game.hexes;

import org.soc.common.core.property.Properties.Description;
import org.soc.common.core.property.Properties.Name;
import org.soc.common.game.*;
import org.soc.common.game.Resource.Gold;
import org.soc.common.game.board.*;
import org.soc.common.internationalization.*;
import org.soc.common.views.meta.*;
import org.soc.gwt.client.images.*;

import com.google.gwt.resources.client.*;

public class GoldHex extends ResourceHex {
  @Override public Icon icon() {
    return new IconImpl(R.icons().goldHex16(), R.icons().goldHex32(), R.icons().goldHex48());
  }
  @Override public Name name() {
    return new Name.Impl(I.get().constants().goldHex());
  }
  @Override public Description description() {
    return new Description.Impl(I.get().constants().goldHexDescription());
  }
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