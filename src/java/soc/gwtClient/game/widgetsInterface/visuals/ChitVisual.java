package soc.gwtClient.game.widgetsInterface.visuals;

import soc.common.board.Chit;

public interface ChitVisual extends PieceVisual
{
    public Chit getChit();
    public ChitVisual setChit(Chit chit);
}
