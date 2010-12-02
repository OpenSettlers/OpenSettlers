package soc.common.board.resources;

public class Timber extends Resource
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
    public Resource Copy()
    {
        return new Timber();
    }

}
