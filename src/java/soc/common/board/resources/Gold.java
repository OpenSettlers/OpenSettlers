package soc.common.board.resources;

import soc.common.annotations.SeaFarers;

@SeaFarers
public class Gold extends AbstractResource
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
    public AbstractResource Copy()
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

    @Override
    public Resource copy()
    {
        return new Gold();
    }
}
