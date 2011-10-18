package org.soc.common.game.hexes;

import org.soc.common.core.property.Properties.Description;
import org.soc.common.core.property.Properties.Name;
import org.soc.common.game.*;
import org.soc.common.game.Resource.Timber;
import org.soc.common.internationalization.*;
import org.soc.common.views.meta.*;
import org.soc.gwt.client.images.*;

import com.google.gwt.resources.client.*;

public class TimberHex extends ResourceHex {
  @Override public Icon icon() {
    return new IconImpl(
            R.icons().timberHex16(),
            R.icons().timberHex32(),
            R.icons().timberHex48());
  }
  @Override public Name name() {
    return new Name.Impl(I.get().constants().timberHex());
  }
  @Override public Description description() {
    return new Description.Impl(I.get().constants().timberHexDescription());
  }
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
