package org.soc.gwt.client.editor;

import org.soc.common.game.Territory;
import org.soc.common.game.actions.ActionOnBoard;

public class MapEditor implements IMapEditor
{
  protected ActionOnBoard editBehaviour;
  protected Territory currentTerritory;

  @Override public Territory getCurrentTerritory()
  {
    return currentTerritory;
  }
}
