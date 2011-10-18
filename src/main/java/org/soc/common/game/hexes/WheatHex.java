package org.soc.common.game.hexes;

import org.soc.common.core.property.Properties.Description;
import org.soc.common.core.property.Properties.Name;
import org.soc.common.game.*;
import org.soc.common.game.Resource.Wheat;
import org.soc.common.views.meta.*;
import org.soc.gwt.client.images.*;

import com.google.gwt.resources.client.*;

import static org.soc.common.internationalization.I.*;

public class WheatHex extends ResourceHex {
  @Override public Icon icon() {
    return new IconImpl(R.icons().wheatHex16(), R.icons().wheatHex32(), R.icons().wheatHex48());
  }
  @Override public Name name() {
    return new Name.Impl(constants.wheatHex());
  }
  @Override public Description description() {
    return new Description.Impl(constants.wheatHexDescription());
  }
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
