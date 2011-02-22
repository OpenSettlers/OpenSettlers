package soc.common.game;

import soc.common.ui.meta.HasMeta;

/*
 * Any item which generates (a) VictoryPoint(s) implements this interface
 */
public interface VictoryPointItem extends HasMeta
{
    public int getVictoryPoints();
}
