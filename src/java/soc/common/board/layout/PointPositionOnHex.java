package soc.common.board.layout;

public enum PointPositionOnHex
{
    //          TopMiddle,
    //              ^
    //    TopLeft  /  \  TopRight
    //            |    |
    //            |    |
    // BottomLeft  \  /  BottomRight
    //               +    
    //         BottomMiddle
    TOPMIDDLE,
    TOPRIGHT,
    BOTTOMRIGHT,
    BOTTOMMIDDLE,
    BOTTOMLEFT,
    TOPLEFT;
}
