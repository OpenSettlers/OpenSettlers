package org.soc.common.board.settings;

import java.io.Serializable;

import org.soc.common.views.meta.HasMeta;


/*
 * A setting specific to a board, to be used in the game
 */
public interface BoardSetting extends HasMeta, Serializable
{
    /*
     * Applies the setting to given BoardSettings instance
     */
    public void set(BoardSettings boardSettings);
}
