package soc.common.board.routing;

/*
 * Graph edge
 */
public interface IGraphSide
{
    // If *anything* can be built on the side.
    // Usually true, but for example false for sides containing a DiscoveryHex
    public boolean isBuildable();
    
    /*
     * Whether or not a side for land can be built on it
     */
    public boolean isBuildableLand();
    
    /*
     * Whether or not a side for sea can be built on it
     * True for ships, possibly true for bridges
     */
    public boolean isBuildableSea();
    
}
