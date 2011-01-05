package soc.common.client.visuals.game;

import soc.common.board.HexSide;
import soc.common.client.visuals.PieceVisual;

public interface ISideVisual extends PieceVisual
{
    public HexSide getHexSide();
}
