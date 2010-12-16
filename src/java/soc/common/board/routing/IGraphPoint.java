package soc.common.board.routing;

/*
 * Graph vertex
 */
public interface IGraphPoint
{
    /*
     * Whether or not there can be built on 
     * 
     * Not true for any known PointPiece (maybe an oil platform)
     */
    public boolean isSeaBuildable();
    
    /*
     * Whether or not it is possible for land pieces to build on this point
     * 
     * Example: false when point consists of three seahexes
     */
    public boolean isLandBuildable();
    
    /*
     * Whether or not *anything* can be built on it.
     * 
     *  False for for example for DiscoveryHex
     */
    public boolean isBuildable();
}
