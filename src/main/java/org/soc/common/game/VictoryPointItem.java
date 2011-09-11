package org.soc.common.game;

import org.soc.common.views.meta.Meta;

/** Any item which generates (a) VictoryPoint(s) implements this interface */
public interface VictoryPointItem extends Meta {
  public int victoryPoints();
}
