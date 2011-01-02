package soc.common.client.behaviour;

import soc.common.client.visuals.PieceVisual;
import soc.common.client.visuals.board.BoardVisual;

/*
 * Default behaviour of an IBoardVisual, shows information of clicked item
 * and highlights hovered PieceVisual
 */
public class DefaultBehaviour implements InteractionBehaviour
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
