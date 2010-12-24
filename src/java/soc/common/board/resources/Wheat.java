package soc.common.board.resources;

public class Wheat extends AbstractResource
{

    /* (non-Javadoc)
     * @see soc.common.board.resources.Resource#getColor()
     */
    @Override
    public String getColor()
    {
        return "Yellow";
    }

    @Override
    public AbstractResource Copy()
    {
        return new Wheat();
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
        return new Wheat();
    }
}
