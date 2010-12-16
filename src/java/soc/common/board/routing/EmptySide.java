package soc.common.board.routing;

/*
 * Represents an empty graph side (edge)
 */
public class EmptySide implements IGraphSide
{

    @Override
    public boolean isBuildable()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isBuildableLand()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isBuildableSea()
    {
        // TODO Auto-generated method stub
        return false;
    }

}
