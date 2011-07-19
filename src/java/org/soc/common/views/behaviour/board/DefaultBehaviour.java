package org.soc.common.views.behaviour.board;

import org.soc.common.views.widgetsInterface.visuals.BoardVisual;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual;

/*
 * Default behaviour of an IBoardVisual, shows information of clicked item
 * and highlights hovered PieceVisual
 */
public class DefaultBehaviour implements BoardBehaviour
{
    @Override
    public void clicked(PieceVisual pieceVisual, BoardVisual board)
    {

    }

    /*
     * highlights hovered IPieceVisual
     * @see org.soc.common.client.behaviour.IInteractionBehaviour#mouseEnter(org.soc.common.client.visuals.IPieceVisual, org.soc.common.client.visuals.board.IBoardVisual)
     */
    @Override
    public void mouseEnter(PieceVisual pieceVisual, BoardVisual board)
    {
        pieceVisual.setSelected(true);
    }

    /*
     * De-highlights hovered IPieceVisual
     * @see org.soc.common.client.behaviour.IInteractionBehaviour#mouseLeave(org.soc.common.client.visuals.IPieceVisual, org.soc.common.client.visuals.board.IBoardVisual)
     */
    @Override
    public void mouseOut(PieceVisual pieceVisual, BoardVisual board)
    {
        pieceVisual.setSelected(false);
    }

}
