package soc.common.board.pieces.abstractPieces;

import java.io.Serializable;

import soc.gwtClient.game.widgetsInterface.visuals.PieceVisual;
import soc.gwtClient.game.widgetsInterface.visuals.VisualFactory;

public interface Piece extends Serializable
{
    public PieceVisual createPiece(VisualFactory visualFactory);
}
