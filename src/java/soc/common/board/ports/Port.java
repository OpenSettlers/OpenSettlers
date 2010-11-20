package soc.common.board.ports;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import soc.common.board.HexLocation;
import soc.common.board.HexSide;
import soc.common.board.RotationPosition;
import soc.common.board.resources.Resource;
import soc.common.board.resources.ResourceList;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public abstract class Port
{
    protected HexLocation hexLocation;
    protected HexSide hexSide;
    protected RotationPosition rotationPosition;

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
        return hexLocation;
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
    public Port setHexLocation(HexLocation hexLocation) throws Exception
    {
        hexSide = hexLocation.GetSideLocation(rotationPosition);
        
        return this;
    }
    
    public int getInAmount()
    {
        throw new NotImplementedException();
    }
    public int getOutAmount()
    {
        throw new NotImplementedException();
    }


    public Port()
    {
        super();
    }
    
    public Port(HexLocation hexLocation)
    {
        super();
        this.hexLocation = hexLocation;
    }
    
    /*
     * Returns amount of gold tradeable for given resource
     */
    public int divide(ResourceList resources, Resource type)
    {
        // Have something to return, default on 0
        int amountGold=0;

        // Filter the list of resources by given type
        List<Resource> resourcesOfType = resources.ofType(type);
        
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
