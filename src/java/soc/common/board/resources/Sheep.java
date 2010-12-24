package soc.common.board.resources;

public class Sheep extends AbstractResource
{
    /* (non-Javadoc)
     * @see soc.common.board.resources.Resource#getColor()
     */
    @Override
    public String getColor()
    {
        return "Green";
    }

    @Override
    public AbstractResource Copy()
    {
        return new Sheep();
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
        return new Sheep();
    }
}
