package soc.common.board.resources;

import soc.common.annotations.SeaFarers;

@SeaFarers
public class Gold extends AbstractResource
{
    private static final long serialVersionUID = 6496837317375783863L;

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.resources.Resource#getColor()
     */
    @Override
    public String getColor()
    {
        return "Gold";
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
        return new Gold();
    }

    @Override
    public int hashCode()
    {
        return 3;
    }
}
