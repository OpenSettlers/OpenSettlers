package org.soc.common.board.layout;

import java.util.List;

public interface HexSide extends HasNeighbours
{

    HexLocation getHex1();

    HexLocation getHex2();

    HexPoint getHexPoint1();

    HexPoint getHexPoint2();

    HexLocation getHighestOrLeftestHex();

    /*
     * Returns a list of (maximum) three points neighbouring this
     */
    List<HexPoint> getNeighbourPoints();

    /*
     * Returns the direction this side is pointing to
     */
    SideDirection getDirection();

    HexPoint getOtherPoint(HexPoint first);

    HexLocation getOtherLocation(HexLocation first);

}