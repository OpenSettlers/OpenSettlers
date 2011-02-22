package soc.common.board.hexes;

import soc.common.board.Chit;

import com.google.gwt.event.shared.HandlerRegistration;

public abstract class ResourceHex extends AbstractHex
{
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
    public ResourceHex setChit(Chit c)
    {
        this.chit = c;

        eventBus.fireEvent(new ChitChangedEvent(c));

        return this;
    }

    // /*
    // * (non-Javadoc)
    // *
    // * @see soc.common.board.hexes.Hex#Copy()
    // */
    // @Override
    // public AbstractHex copy()
    // {
    // ResourceHex rh = new ResourceHex().setChit(new Chit(5)).setResource(
    // getResource());
    // rh.setTerritory(territory);
    // rh.setChit(chit.copy());
    // return rh;
    // }

    public HandlerRegistration addChitChangedEventHandler(
            ChitChangedEventHandler handler)
    {
        return eventBus.addHandler(ChitChangedEvent.TYPE, handler);
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.Hex#getColor()
     */
    @Override
    public String getColor()
    {
        return getResource().getColor();
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.Hex#getName()
     */
    @Override
    public String getName()
    {
        return getResource().getName() + "Hex";
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.IHex#isBuildableLand()
     */
    @Override
    public boolean isBuildableLand()
    {
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.IHex#isBuildableSea()
     */
    @Override
    public boolean isBuildableSea()
    {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.IHex#isPartOfGame()
     */
    @Override
    public boolean isPartOfGame()
    {
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.IHex#isPiratePlaceable()
     */
    @Override
    public boolean isPiratePlaceable()
    {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.IHex#isRobberPlaceable()
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
    public boolean hasResource()
    {
        return true;
    }
}
