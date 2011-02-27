package soc.common.board.settings;

import soc.common.views.meta.Meta;

/*
 *  Whether or not players can earn traderoute points to connect territories
 */
public class UsesTradeRoutes implements BoardSetting
{
    private static final long serialVersionUID = 7257705418623102088L;
    private boolean tradeRoutes;

    @Override
    public void set(BoardSettings boardSettings)
    {
        if (boardSettings.getUseTradeRoutes() == null)
            boardSettings.setUseTradeRoutes(this);

        boardSettings.getUseTradeRoutes().setTradeRoutes(tradeRoutes);
    }

    /**
     * @return the tradeRoutes
     */
    public boolean isTradeRoutes()
    {
        return tradeRoutes;
    }

    /**
     * @param tradeRoutes
     *            the tradeRoutes to set
     */
    public UsesTradeRoutes setTradeRoutes(boolean tradeRoutes)
    {
        this.tradeRoutes = tradeRoutes;
        return this;
    }

    @Override
    public Meta getMeta()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
