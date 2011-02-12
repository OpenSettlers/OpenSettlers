package soc.common.board.resources;

public class Wheat extends AbstractResource
{

    /**
     * 
     */
    private static final long serialVersionUID = -7393820454171830461L;

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.resources.Resource#getColor()
     */
    @Override
    public String getColor()
    {
        return "Yellow";
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
        return new Wheat();
    }

    @Override
    public int hashCode()
    {
        return 7;
    }
}
