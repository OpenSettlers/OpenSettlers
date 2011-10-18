package org.soc.common.game.hexes;

import org.soc.common.core.property.Properties.Description;
import org.soc.common.core.property.Properties.Name;
import org.soc.common.game.*;
import org.soc.common.game.Resource.Diamond;
import org.soc.common.views.meta.*;
import org.soc.gwt.client.images.*;

import com.google.gwt.resources.client.*;

public class DiamondHex extends ResourceHex {
  @Override public Icon icon() {
    return new IconImpl(
            R.icons().diamondHex16(),
            R.icons().diamondHex32(),
            R.icons().diamondHex48());
  }
  @Override public Name name() {
    return null;
  }
  @Override public Description description() {
    return null;
  }
  @Override public Resource resource() {
    return new Diamond();
  }
  @Override public Hex copy() {
    DiamondHex diamondHex = new DiamondHex();
    diamondHex.setChit(copyChit());
    diamondHex.setLocation(hexLocation);
    diamondHex.setTerritory(territory);
    return diamondHex;
  }
  @Override public ImageResource texture() {
    return R.images().diamondHex();
  }
}