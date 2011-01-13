package soc.common.board.resources;

import soc.common.annotations.Sea3D;

@Sea3D
public class Diamond extends AbstractResource
{

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.resources.Resource#getColor()
     */
    @Override
    public String getColor()
    {
        return "Grey";
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.resources.Resource#isTradeable()
     */
    @Override
    public boolean isTradeable()
    {
        return false;
    }

    @Override
    public Resource copy()
    {
        return new Diamond();
    }

    @Override
    public int hashCode()
    {
        return 2;
    }

}
