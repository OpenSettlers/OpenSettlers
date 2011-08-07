package org.soc.common.views.widgetsInterface.visuals;

import org.soc.common.board.chits.Chit;

public interface ChitVisual extends PieceVisual
{
    public Chit getChit();
    public ChitVisual setChit(Chit chit);
}
