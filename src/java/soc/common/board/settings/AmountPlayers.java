package soc.common.board.settings;

import soc.common.views.meta.Meta;

public class AmountPlayers implements BoardSetting
{
    private static final long serialVersionUID = 1333406066412406728L;
    private int amountPlayers = 4;

    @Override
    public void set(BoardSettings boardSettings)
    {
        boardSettings.getAmountPlayers().setAmountPlayers(amountPlayers);
    }

    /**
     * @return the amountPlayers
     */
    public int getAmountPlayers()
    {
        return amountPlayers;
    }

    /**
     * @param amountPlayers
     *            the amountPlayers to set
     */
    public AmountPlayers setAmountPlayers(int amountPlayers)
    {
        if (amountPlayers > 2 && amountPlayers < 7)
            this.amountPlayers = amountPlayers;
        return this;
    }

    @Override
    public Meta getMeta()
    {
        // TODO Auto-generated method stub
        return null;
    }
}