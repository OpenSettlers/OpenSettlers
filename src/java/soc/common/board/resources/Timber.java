package soc.common.board.resources;

public class Timber extends AbstractResource
{

    /* (non-Javadoc)
     * @see soc.common.board.resources.Resource#getColor()
     */
    @Override
    public String getColor()
    {
        return "DarkGreen";
    }

    @Override
    public AbstractResource Copy()
    {
        return new Timber();
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
        return new Timber();
    }
}
