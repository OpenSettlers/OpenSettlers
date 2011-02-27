package soc.common.board.chits;

import java.io.Serializable;

public interface Chit extends Serializable
{
    public Chit copy();

    /**
     * @return the number
     */
    public int getNumber();

    public boolean isRed();

    public int getChance();
}