package soc.common.board.resources;

public class Timber extends AbstractResource
{

    /**
     * 
     */
    private static final long serialVersionUID = -6537069648667733465L;

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.resources.Resource#getColor()
     */
    @Override
    public String getColor()
    {
        return "DarkGreen";
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
        return new Timber();
    }

    @Override
    public int hashCode()
    {
        return 6;
    }
}
