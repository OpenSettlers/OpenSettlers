package org.soc.common.board.hexes;

import org.soc.common.board.chits.Chit;

public abstract class ResourceHex extends AbstractHex
{
    private static final long serialVersionUID = 7086634587082441341L;
    protected Chit chit;

    /**
     * @return the chit
     */
    public Chit getChit()
    {
        return chit;
    }

    /**
     * @param chit
     *            the chit to set
     */
    public AbstractHex setChit(Chit c)
    {
        this.chit = c;

        eventBus.fireEvent(new ChitChangedEvent(c));

        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.board.hexes.Hex#getColor()
     */
    @Override
    public String getColor()
    {
        return getResource().getColor();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.board.hexes.Hex#getName()
     */
    @Override
    public String getName()
    {
        return getResource().getName() + "Hex";
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.board.hexes.IHex#isBuildableLand()
     */
    @Override
    public boolean isBuildableLand()
    {
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.board.hexes.IHex#isBuildableSea()
     */
    @Override
    public boolean isBuildableSea()
    {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.board.hexes.IHex#isPartOfGame()
     */
    @Override
    public boolean isPartOfGame()
    {
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.board.hexes.IHex#isPiratePlaceable()
     */
    @Override
    public boolean isPiratePlaceable()
    {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.board.hexes.IHex#isRobberPlaceable()
     */
    @Override
    public boolean isRobberPlaceable()
    {
        return true;
    }

    @Override
    public boolean canHaveChit()
    {
        return true;
    }

    @Override
    public boolean hasChit()
    {
        return chit != null;
    }

    @Override
    public boolean canHaveResource()
    {
        return true;
    }
}
