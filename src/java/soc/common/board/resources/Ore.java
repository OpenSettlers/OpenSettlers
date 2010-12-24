package soc.common.board.resources;

public class Ore extends AbstractResource
{
    /* (non-Javadoc)
     * @see soc.common.board.resources.Resource#getColor()
     */
    @Override
    public String getColor()
    {
        return "Purple";
    }

    @Override
    public AbstractResource Copy()
    {
        return new Ore();
    }
    /* (non-Javadoc)
     * @see soc.common.board.resources.AbstractResource#isTradeable()
     */
    @Override
    public boolean isTradeable()
    {
        return true;
    }

    @Override
    public Resource copy()
    {
        return new Ore();
    }
}
