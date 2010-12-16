package soc.common.client.visuals.game;

import soc.common.board.HexSide;
import soc.common.client.visuals.IPieceVisual;

public interface ISideVisual extends IPieceVisual
{
    public HexSide getHexSide();
}