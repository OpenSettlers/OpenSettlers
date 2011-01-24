package soc.common.board.pieces.pieceLists;

import soc.common.board.HexSide;
import soc.common.board.pieces.Ship;

public class ShipList extends PlayerPieceList<Ship>
{
    public boolean contains(HexSide side)
    {
        for (Ship ship : pieces)
            if (ship.getSide().equals(side))
                return true;

        return false;
    }
}
