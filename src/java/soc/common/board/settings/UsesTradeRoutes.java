package soc.common.board.settings;

/*
 *  Whether or not players can earn traderoute points to connect territories
 */
public class UsesTradeRoutes implements BoardSetting
{
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

}
