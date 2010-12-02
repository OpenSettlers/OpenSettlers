package soc.common.board.resources;

public class Clay extends Resource
{

    /* (non-Javadoc)
     * @see soc.common.board.resources.Resource#getColor()
     */
    @Override
    public String getColor()
    {
        return "Red";
    }

    @Override
    public Resource Copy()
    {
        return new Clay();
    }
}
