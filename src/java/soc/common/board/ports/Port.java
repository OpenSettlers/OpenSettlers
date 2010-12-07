package soc.common.board.ports;

import java.util.List;

import soc.common.board.HexLocation;
import soc.common.board.HexSide;
import soc.common.board.RotationPosition;
import soc.common.board.resources.Resource;
import soc.common.board.resources.ResourceList;

public class Port
{
    protected HexLocation seaLocation;
    protected HexLocation landLocation;
    protected HexSide hexSide;
    protected RotationPosition rotationPosition;

    
    /**
     * @return the landLocation
     */
    public HexLocation getLandLocation()
    {
        return landLocation;
    }
    
    /**
     * @return the resource
     */
    public Resource getResource()
    {
        return null;
    }

    /**
     * @return the hexLocation
     */
    public HexLocation getHexLocation()
    {
        return seaLocation;
    }

    /**
     * @return the hexSide
     */
    public HexSide getHexSide()
    {
        return hexSide;
    }

    /**
     * @param hexSide the hexSide to set
     */
    public Port setHexSide(HexSide hexSide)
    {
        this.hexSide = hexSide;
        
        return this;
    }

    /**
     * @return the rotationPosition
     */
    public RotationPosition getRotationPosition()
    {
        return rotationPosition;
    }

    /**
     * @param hexLocation the hexLocation to set
     * @throws Exception 
     */
    public Port setHexLocation(HexLocation hexLocation)
    {
        this.seaLocation = hexLocation;
        
        hexSide = hexLocation.getSideLocation(rotationPosition);
        
        return this;
    }
    
    public int getInAmount()
    {
        throw new RuntimeException();
    }
    public int getOutAmount()
    {
        throw new RuntimeException();
    }

    public Port()
    {
        super();
    }
    
    public Port(HexLocation hexLocation)
    {
        super();
        
        this.seaLocation = hexLocation;
    }    
    
    public Port(HexLocation hexLocation, RotationPosition rotationPosition)
    {
        super();
        
        this.rotationPosition = rotationPosition;
        this.setHexLocation(hexLocation);
        this.landLocation = hexSide.getOtherLocation(seaLocation);
    }
    
    /*
     * Returns amount of gold tradeable for given resource
     */
    public int divide(ResourceList resources, Resource type)
    {
        // Have something to return, default on 0
        int amountGold=0;

        // Filter the list of resources by given type
        ResourceList resourcesOfType = resources.ofType(type);
        
        // When we have at least two resources,  determine amount of gold we can trade 
        if (resourcesOfType.size() >= 2)
        {
            // Amount of gold is number of times we can trade by inAmount, times
            // the amount of cards we get per trade
            amountGold = (resourcesOfType.size() / getInAmount()) * getOutAmount();
        }
        
        return amountGold;
    }

}
