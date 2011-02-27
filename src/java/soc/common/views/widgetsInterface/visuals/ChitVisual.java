package soc.common.views.widgetsInterface.visuals;

import soc.common.board.chits.Chit;

public interface ChitVisual extends PieceVisual
{
    public Chit getChit();
    public ChitVisual setChit(Chit chit);
}
