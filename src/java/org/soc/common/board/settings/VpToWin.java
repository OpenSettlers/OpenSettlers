package org.soc.common.board.settings;

import org.soc.common.views.meta.Meta;

/*
 *  Amount of vp to win on this board
 */
public class VpToWin implements BoardSetting
{
    private static final long serialVersionUID = -3806494586457665700L;
    private int vpToWin = 10;

    @Override
    public void set(BoardSettings boardSettings)
    {
        boardSettings.getVpToWin().setVpToWin(vpToWin);
    }

    /**
     * @return the vpToWin
     */
    public int getVpToWin()
    {
        return vpToWin;
    }

    /**
     * @param vpToWin
     *            the vpToWin to set
     */
    public VpToWin setVpToWin(int vpToWin)
    {
        this.vpToWin = vpToWin;
        return this;
    }

    @Override
    public Meta getMeta()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
