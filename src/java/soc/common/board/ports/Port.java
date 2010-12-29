package soc.common.board.ports;

import soc.common.board.HexLocation;
import soc.common.board.HexSide;
import soc.common.board.RotationPosition;
import soc.common.board.resources.AbstractResource;
import soc.common.board.resources.Resource;
import soc.common.board.resources.ResourceList;

public interface Port
{

    /**
     * @return the landLocation
     */
    public abstract HexLocation getLandLocation();

    /**
     * @return the resource
     */
    public abstract AbstractResource getResource();

    /**
     * @return the hexLocation
     */
    public abstract HexLocation getHexLocation();

    /**
     * @return the hexSide
     */
    public abstract HexSide getHexSide();

    /**
     * @param hexSide
     *            the hexSide to set
     */
    public abstract Port setHexSide(HexSide hexSide);

    /**
     * @return the rotationPosition
     */
    public abstract RotationPosition getRotationPosition();

    public abstract int getInAmount();

    public abstract int getOutAmount();

    /*
     * Returns amount of gold tradeable for given resource
     */
    public abstract int divide(ResourceList resources, Resource type);

    public abstract boolean canTrade(Resource resource);

    /**
     * @param hexLocation
     *            the hexLocation to set
     * @throws Exception
     */
    public Port setHexLocation(HexLocation hexLocation);

    public Port copy();

    public String getColor();
}