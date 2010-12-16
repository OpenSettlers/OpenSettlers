package soc.common.board.resources;

import soc.common.annotations.SeaFarers;

@SeaFarers
public class Gold extends Resource
{

    /* (non-Javadoc)
     * @see soc.common.board.resources.Resource#getColor()
     */
    @Override
    public String getColor()
    {
        return "Gold";
    }

    @Override
    public Resource Copy()
    {
        return new Gold();
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
