package org.soc.common.game.settings;

import org.soc.common.views.meta.Meta;

public class MaximumTradesPerTurn implements GameSetting
{
    private static final long serialVersionUID = 2909357976269916948L;
    private int maxTradesPerTurn = 3;

    @Override
    public void executeGameSetting(GameSettings gameSettings)
    {
        gameSettings.setMaximumTradesPerTurn(this);
    }

    @Override
    public Meta getMeta()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @return the maxTradesPerTurn
     */
    public int getMaxTradesPerTurn()
    {
        return maxTradesPerTurn;
    }

    /**
     * @param maxTradesPerTurn
     *            the maxTradesPerTurn to set
     */
    public MaximumTradesPerTurn setMaxTradesPerTurn(int maxTradesPerTurn)
    {
        this.maxTradesPerTurn = maxTradesPerTurn;
        return this;
    }

}
