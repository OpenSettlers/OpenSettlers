package soc.common.board.pieces.abstractPieces;

import java.io.Serializable;

import soc.common.views.meta.HasMeta;
import soc.common.views.widgetsInterface.visuals.PieceVisual;
import soc.common.views.widgetsInterface.visuals.VisualFactory;

public interface Piece extends Serializable, HasMeta
{
    public PieceVisual createPiece(VisualFactory visualFactory);
}
