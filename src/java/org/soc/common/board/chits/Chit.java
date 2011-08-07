package org.soc.common.board.chits;

import java.io.Serializable;

import org.soc.common.views.meta.HasMeta;


public interface Chit extends Serializable, HasMeta
{
    public Chit copy();

    /** @return the number */
    public int getNumber();

    public boolean isRed();

    public int getChance();
}