package soc.common.board.resources;

public class Ore extends Resource
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
    public Resource Copy()
    {
        return new Ore();
    }

}
