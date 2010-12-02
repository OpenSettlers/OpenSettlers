package soc.common.board.resources;

public class Wheat extends Resource
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
    public Resource Copy()
    {
        return new Wheat();
    }

}
