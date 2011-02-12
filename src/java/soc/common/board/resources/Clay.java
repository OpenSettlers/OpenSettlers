package soc.common.board.resources;

public class Clay extends AbstractResource
{
    private static final long serialVersionUID = -4151296314674387899L;

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.resources.Resource#getColor()
     */
    @Override
    public String getColor()
    {
        return "Red";
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
        return new Clay();
    }

    @Override
    public int hashCode()
    {
        return 1;
    }
}
