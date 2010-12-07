package soc.common.board.hexes;

import com.google.gwt.event.shared.*;

import soc.common.board.Chit;
import soc.common.board.resources.Resource;

public class ResourceHex extends LandHex
{
    private Resource resource;
    private Chit chit;
  
    /**
     * @return the chit
     */
    public Chit getChit()
    {
        return chit;
    }

    /**
     * @param chit the chit to set
     */
    public ResourceHex setChit(Chit c)
    {
        this.chit = c;
        
        eventBus.fireEvent(new ChitChangedEvent(c));
        
        return this;
    }

    /* (non-Javadoc)
     * @see soc.common.board.hexes.Hex#Copy()
     */
    @Override
    public Hex copy()
    {
        ResourceHex rh = new ResourceHex()
            .setChit(new Chit(5))
            .setResource(getResource());
        rh.setTerritory(territory);
        return rh;
    }

    /**
     * @return the production
     */
    public Resource getResource()
    {
        return resource;
    }
    
    /**
     * @param resource the resource to set
     */
    public ResourceHex setResource(Resource resource)
    {
        this.resource = resource;
    
        return this;
    }

    /*
     *  At init time, we want a resource
     */
    public ResourceHex(Resource resource)
    {
        super();
        this.resource = resource;
    }
    
    public ResourceHex()
    {
    }

    public HandlerRegistration addChitChangedEventHandler(ChitChangedEventHandler handler)
    {
        return eventBus.addHandler(ChitChangedEvent.TYPE, handler);
    }

    /* (non-Javadoc)
     * @see soc.common.board.hexes.Hex#getColor()
     */
    @Override
    public String getColor()
    {
        return resource.getColor();
    }

    /* (non-Javadoc)
     * @see soc.common.board.hexes.Hex#getName()
     */
    @Override
    public String getName()
    {
        return resource.getName() + "Hex";
    }
}
