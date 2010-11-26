package soc.common.client.visuals.game;

import java.util.List;

import soc.common.board.HexSide;
import soc.common.client.visuals.IPieceVisual;

public interface IHexSidesVisual extends IPieceVisual
{
    public void updateSides(List<HexSide> sides);
}
