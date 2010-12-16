package soc.common.board.pieces;

import soc.common.board.HexSide;

/*
 * PlayerPiece built on a HexSide. Examples: Road, ship
 */
public interface ISidePiece
{
    public HexSide getSide();
    public boolean canConnectRoad(ISidePiece otherSide);
}
