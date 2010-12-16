package soc.common.board.resources;

import soc.common.annotations.Sea3D;

@Sea3D
public class Diamond extends Resource
{

    /* (non-Javadoc)
     * @see soc.common.board.resources.Resource#getColor()
     */
    @Override
    public String getColor()
    {
        return "Grey";
    }

    @Override
    public Resource Copy()
    {
        return new Diamond();
    }

    /* (non-Javadoc)
     * @see soc.common.board.resources.Resource#isTradeable()
     */
    @Override
    public boolean isTradeable()
    {
        return false; 
    }

}
