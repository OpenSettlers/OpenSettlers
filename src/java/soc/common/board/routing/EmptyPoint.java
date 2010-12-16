package soc.common.board.routing;

/*
 * Represents an empty graph point (vertex)
 */
public class EmptyPoint implements IGraphPoint
{

    @Override
    public boolean isBuildable()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isLandBuildable()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isSeaBuildable()
    {
        // TODO Auto-generated method stub
        return false;
    }

}
