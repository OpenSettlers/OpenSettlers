package soc.common.board.resources;

public class Ore extends AbstractResource
{
    /**
     * 
     */
    private static final long serialVersionUID = -8778560987710166668L;

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.resources.Resource#getColor()
     */
    @Override
    public String getColor()
    {
        return "Purple";
    }

    /*
     * (non-Javadoc)
     * 
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

    @Override
    public int hashCode()
    {
        return 4;
    }
}
