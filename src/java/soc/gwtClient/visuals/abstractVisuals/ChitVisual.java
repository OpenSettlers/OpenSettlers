package soc.gwtClient.visuals.abstractVisuals;

import soc.common.board.Chit;

public interface ChitVisual extends PieceVisual
{
    public Chit getChit();
    public ChitVisual setChit(Chit chit);
}
