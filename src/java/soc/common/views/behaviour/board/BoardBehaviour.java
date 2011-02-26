package soc.common.views.behaviour.board;

import soc.common.views.widgetsInterface.visuals.BoardVisual;
import soc.common.views.widgetsInterface.visuals.PieceVisual;

public interface BoardBehaviour
{
    public void clicked(PieceVisual pieceVisual, BoardVisual board);
    public void mouseEnter(PieceVisual pieceVisual, BoardVisual board);
    public void mouseOut(PieceVisual pieceVisual, BoardVisual board);
}
