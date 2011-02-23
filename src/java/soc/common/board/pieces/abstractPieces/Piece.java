package soc.common.board.pieces.abstractPieces;

import java.io.Serializable;

import soc.common.ui.meta.HasMeta;
import soc.gwtClient.game.widgetsInterface.visuals.PieceVisual;
import soc.gwtClient.game.widgetsInterface.visuals.VisualFactory;

public interface Piece extends Serializable, HasMeta
{
    public PieceVisual createPiece(VisualFactory visualFactory);
}
