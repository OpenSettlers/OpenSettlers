package soc.common.client.visuals.board;

import soc.common.board.Chit;
import soc.common.client.visuals.IPieceVisual;

public interface IChitVisual extends IPieceVisual
{
    public Chit getChit();
    public IChitVisual setChit(Chit chit);
}
