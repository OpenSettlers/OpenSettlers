package org.soc.common.game;

import org.soc.common.views.meta.HasMeta;

/*
 * Any item which generates (a) VictoryPoint(s) implements this interface
 */
public interface VictoryPointItem extends HasMeta
{
    public int getVictoryPoints();
}
