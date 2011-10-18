package org.soc.common.game.hexes;

import org.soc.common.core.property.Properties.Description;
import org.soc.common.core.property.Properties.Name;
import org.soc.common.game.*;
import org.soc.common.game.Resource.Ore;
import org.soc.common.internationalization.*;
import org.soc.common.views.meta.*;
import org.soc.gwt.client.images.*;

import com.google.gwt.resources.client.*;

public class OreHex extends ResourceHex {
  @Override public Icon icon() {
    return new IconImpl(R.icons().oreHex16(), R.icons().oreHex32(), R.icons().oreHex48());
  }
  @Override public Name name() {
    return new Name.Impl(I.get().constants().oreHex());
  }
  @Override public Description description() {
    return new Description.Impl(I.get().constants().oreHexDescription());
  }
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
