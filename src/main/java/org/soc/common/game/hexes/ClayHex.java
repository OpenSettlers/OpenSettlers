package org.soc.common.game.hexes;

import org.soc.common.core.property.Properties.Description;
import org.soc.common.core.property.Properties.Name;
import org.soc.common.game.*;
import org.soc.common.game.Resource.Clay;
import org.soc.common.internationalization.*;
import org.soc.common.views.meta.*;
import org.soc.gwt.client.images.*;

import com.google.gwt.resources.client.*;

public class ClayHex extends ResourceHex {
  @Override public Icon icon() {
    return new IconImpl(R.icons().clayHex16(), R.icons().clayHex32(), R.icons().clayHex48());
  }
  @Override public Name name() {
    return new Name.Impl(I.get().constants().clayHex());
  }
  @Override public Description description() {
    return new Description.Impl(I.get().constants().clayHexDescription());
  }
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
