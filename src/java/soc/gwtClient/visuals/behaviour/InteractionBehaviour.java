package soc.gwtClient.visuals.behaviour;

import soc.gwtClient.visuals.abstractVisuals.BoardVisual;
import soc.gwtClient.visuals.abstractVisuals.PieceVisual;

public interface InteractionBehaviour
{
    public void clicked(PieceVisual pieceVisual, BoardVisual board);
    public void mouseEnter(PieceVisual pieceVisual, BoardVisual board);
    public void mouseOut(PieceVisual pieceVisual, BoardVisual board);
}
