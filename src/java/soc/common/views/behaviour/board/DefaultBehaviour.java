package soc.common.views.behaviour.board;

import soc.common.views.widgetsInterface.visuals.BoardVisual;
import soc.common.views.widgetsInterface.visuals.PieceVisual;

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
     * @see soc.common.client.behaviour.IInteractionBehaviour#mouseEnter(soc.common.client.visuals.IPieceVisual, soc.common.client.visuals.board.IBoardVisual)
     */
    @Override
    public void mouseEnter(PieceVisual pieceVisual, BoardVisual board)
    {
        pieceVisual.setSelected(true);
    }

    /*
     * De-highlights hovered IPieceVisual
     * @see soc.common.client.behaviour.IInteractionBehaviour#mouseLeave(soc.common.client.visuals.IPieceVisual, soc.common.client.visuals.board.IBoardVisual)
     */
    @Override
    public void mouseOut(PieceVisual pieceVisual, BoardVisual board)
    {
        pieceVisual.setSelected(false);
    }

}
