package soc.common.board.resources;

public class Sheep extends AbstractResource
{
    /**
     * 
     */
    private static final long serialVersionUID = 8756575918873023940L;

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.resources.Resource#getColor()
     */
    @Override
    public String getColor()
    {
        return "LightGreen";
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
        return new Sheep();
    }

    @Override
    public int hashCode()
    {
        return 5;
    }
}
