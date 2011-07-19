package org.soc.common.board.pieces.abstractPieces;

import java.io.Serializable;

import org.soc.common.views.meta.HasMeta;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual;
import org.soc.common.views.widgetsInterface.visuals.VisualFactory;


public interface Piece extends Serializable, HasMeta
{
    public PieceVisual createPiece(VisualFactory visualFactory);
}
