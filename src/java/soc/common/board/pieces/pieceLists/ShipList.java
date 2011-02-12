package soc.common.board.pieces.pieceLists;

import java.io.Serializable;

import soc.common.board.HexSide;
import soc.common.board.pieces.Ship;

public class ShipList extends PlayerPieceList<Ship> implements Serializable
{
    private static final long serialVersionUID = 7485784679594952316L;

    public boolean contains(HexSide side)
    {
        for (Ship ship : pieces)
            if (ship.getSide().equals(side))
                return true;

        return false;
    }
}
