package soc.common.board.settings;

/*
 *  Amount of vp to win on this board
 */
public class VpToWin implements BoardSetting
{
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

}
