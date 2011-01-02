package soc.common.board.ports;

import soc.common.board.HexLocation;
import soc.common.board.HexSide;
import soc.common.board.RotationPosition;
import soc.common.board.resources.AbstractResource;
import soc.common.board.resources.Resource;
import soc.common.board.resources.ResourceList;

public abstract class AbstractPort implements Port
{
    private static final long serialVersionUID = 1247189120735462756L;
    protected HexLocation seaLocation;
    protected HexLocation landLocation;
    protected HexSide hexSide;
    protected RotationPosition rotationPosition;

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.ports.Port#getLandLocation()
     */
    public HexLocation getLandLocation()
    {
        return landLocation;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.ports.Port#getResource()
     */
    public AbstractResource getResource()
    {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.ports.Port#getHexLocation()
     */
    public HexLocation getHexLocation()
    {
        return seaLocation;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.ports.Port#getHexSide()
     */
    public HexSide getHexSide()
    {
        return hexSide;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.ports.Port#setHexSide(soc.common.board.HexSide)
     */
    public Port setHexSide(HexSide hexSide)
    {
        this.hexSide = hexSide;

        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.ports.Port#getRotationPosition()
     */
    public RotationPosition getRotationPosition()
    {
        return rotationPosition;
    }

    /**
     * @param hexLocation
     *            the hexLocation to set
     * @throws Exception
     */
    public Port setHexLocation(HexLocation hexLocation)
    {
        this.seaLocation = hexLocation;

        hexSide = hexLocation.getSideLocation(rotationPosition);

        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.ports.Port#getInAmount()
     */
    public int getInAmount()
    {
        throw new RuntimeException();
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.ports.Port#getOutAmount()
     */
    public int getOutAmount()
    {
        throw new RuntimeException();
    }

    public AbstractPort()
    {
        super();
    }

    public AbstractPort(HexLocation hexLocation)
    {
        super();

        this.seaLocation = hexLocation;
    }

    public AbstractPort(HexLocation hexLocation,
            RotationPosition rotationPosition)
    {
        super();

        this.rotationPosition = rotationPosition;
        this.setHexLocation(hexLocation);
        this.landLocation = hexSide.getOtherLocation(seaLocation);
    }

    /*
     * Returns amount of gold tradeable for given resource
     */
    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.board.ports.Port#divide(soc.common.board.resources.ResourceList
     * , soc.common.board.resources.Resource)
     */
    public int divide(ResourceList resources, Resource type)
    {
        // Have something to return, default on 0
        int amountGold = 0;

        // Filter the list of resources by given type
        ResourceList resourcesOfType = resources.ofType(type);

        // When we have at least two resources, determine amount of gold we can
        // trade
        if (resourcesOfType.size() >= 2)
        {
            // Amount of gold is number of times we can trade by inAmount, times
            // the amount of cards we get per trade
            amountGold = (resourcesOfType.size() / getInAmount())
                    * getOutAmount();
        }

        return amountGold;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.board.ports.Port#canTrade(soc.common.board.resources.Resource)
     */
    public boolean canTrade(Resource resource)
    {
        throw new RuntimeException();
    }
}
