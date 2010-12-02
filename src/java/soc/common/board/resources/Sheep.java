package soc.common.board.resources;

public class Sheep extends Resource
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
    public Resource Copy()
    {
        return new Sheep();
    }

}
