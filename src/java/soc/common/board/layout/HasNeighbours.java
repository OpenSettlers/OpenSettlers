package soc.common.board.layout;

import java.util.List;

public interface HasNeighbours
{
    /*
     * 
     */
    List<HexLocation> getNeighbourLocations();

    /*
     * 
     */
    List<HexSideImpl> getNeighbourSides();

    /*
     * 
     */
    List<HexPoint> getNeighbourPoints();
}
