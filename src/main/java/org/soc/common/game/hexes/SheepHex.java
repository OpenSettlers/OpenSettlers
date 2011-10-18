package org.soc.common.game.hexes;

import org.soc.common.core.property.Properties.Description;
import org.soc.common.core.property.Properties.Name;
import org.soc.common.game.*;
import org.soc.common.game.Resource.Clay;
import org.soc.common.internationalization.*;
import org.soc.common.views.meta.*;
import org.soc.gwt.client.images.*;

import com.google.gwt.resources.client.*;

public class SheepHex extends ResourceHex {
  @Override public Icon icon() {
    return new IconImpl(R.icons().sheepHex16(),
            R.icons().sheepHex32(), R.icons()
                    .sheepHex48());
  }
  @Override public Name name() {
    return new Name.Impl(I.get().constants().sheepHex());
  }
  @Override public Description description() {
    return new Description.Impl(I.get().constants().sheepHexDescription());
  }
  @Override public Resource resource() {
    return new Clay();
  }
  @Override public Hex copy() {
    return new SheepHex()
            .setChit(copyChit())
            .setLocation(hexLocation)
            .setTerritory(territory);
  }
  @Override public ImageResource texture() {
    return R.images().sheepHex();
  }
}
