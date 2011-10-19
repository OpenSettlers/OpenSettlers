package org.soc.common.game.hexes;

import org.soc.common.game.*;
import org.soc.common.game.Resource.Gold;
import org.soc.gwt.client.images.*;

import com.google.gwt.resources.client.*;

public class VolcanoHex extends ResourceHex {
  private Resource resource = new Gold();

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