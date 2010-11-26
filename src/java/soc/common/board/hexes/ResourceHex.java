package soc.common.board.hexes;

import com.google.gwt.event.shared.*;

import soc.common.board.Chit;
import soc.common.board.resources.Resource;

@SuppressWarnings("deprecation")
public class ResourceHex extends LandHex implements HasHandlers
{
    private Resource resource;
    private Chit chit;
    
    
    private HandlerManager handlerManager = new HandlerManager(this);
  
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
        
        fireEvent(new ChitChangedEvent(c));
        
        return this;
    }

    /* (non-Javadoc)
     * @see soc.common.board.hexes.Hex#Copy()
     */
    @Override
    public Hex Copy()
    {
        // TODO Auto-generated method stub
        ResourceHex rh = new ResourceHex()
            .setChit(new Chit(5))
            .setResource(getResource());
        return rh;
        
        //    .setTerritoryID(this.getTerritoryID());
            
            
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
    
        // Enables fluent interface usage
        // http://en.wikipedia.org/wiki/Fluent_interface
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

    @Override
    public void fireEvent(GwtEvent<?> event)
    {
        handlerManager.fireEvent(event);
    }

    public HandlerRegistration addChitChangedEventHandler(ChitChangedEventHandler handler)
    {
        return handlerManager.addHandler(ChitChangedEvent.TYPE, handler);
    }

    /* (non-Javadoc)
     * @see soc.common.board.hexes.Hex#getColor()
     */
    @Override
    public String getColor()
    {
        // TODO Auto-generated method stub
        return resource.getColor();
    }

    /* (non-Javadoc)
     * @see soc.common.board.hexes.Hex#getName()
     */
    @Override
    public String getName()
    {
        // TODO Auto-generated method stub
        return resource.getName() + "Hex";
    }
}
