package org.soc.common.game.hexes;

import org.soc.common.core.property.Properties.Description;
import org.soc.common.core.property.Properties.Name;
import org.soc.common.game.*;
import org.soc.common.game.Resource.Gold;
import org.soc.common.internationalization.*;
import org.soc.common.views.meta.*;
import org.soc.gwt.client.images.*;

import com.google.gwt.resources.client.*;

public class VolcanoHex extends ResourceHex {
  private Resource resource = new Gold();

  @Override public Icon icon() {
    return new IconImpl(null, null, R.icons()
            .volcanoHex32(), null);
  }
  @Override public Name name() {
    return new Name.Impl(I.get().constants().volcanoHex());
  }
  @Override public Description description() {
    return new Description.Impl(I.get().constants().volcanoHexDescription());
  }
  @Override public Resource resource() {
    return resource;
  }
  @Override public String getColor() {
    return "DarkRed";
  }
  @Override public Hex copy() {
    VolcanoHex result = new VolcanoHex();
    result.setChit(copyChit());
    result.setTerritory(territory);
    return result;
  }
  @Override public boolean canHaveChit() {
    return true;
  }
  @Override public boolean hasChit() {
    return chit != null;
  }
  @Override public boolean producesResource() {
    return true;
  }
  @Override public ImageResource texture() {
    return R.images().volcanoHex();
  }
}