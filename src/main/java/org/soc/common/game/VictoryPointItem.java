package org.soc.common.game;

import org.soc.common.core.OpenZettlers.OsModel;

/** Any item which generates (a) VictoryPoint(s) implements this interface */
public interface VictoryPointItem extends OsModel<Integer> {
  public int victoryPoints();
}
