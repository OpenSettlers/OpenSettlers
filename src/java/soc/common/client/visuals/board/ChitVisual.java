package soc.common.client.visuals.board;

import soc.common.board.Chit;
import soc.common.client.visuals.PieceVisual;

public interface ChitVisual extends PieceVisual
{
    public Chit getChit();
    public ChitVisual setChit(Chit chit);
}
