package soc.common.client.behaviour;

import soc.common.client.visuals.IPieceVisual;
import soc.common.client.visuals.board.IBoardVisual;

/*
 * Default behaviour of an IBoardVisual, shows information of clicked item
 * and highlights hovered PieceVisual
 */
public class DefaultBehaviour implements IInteractionBehaviour
{
    @Override
    public void clicked(IPieceVisual pieceVisual, IBoardVisual board)
    {

    }

    /*
     * highlights hovered IPieceVisual
     * @see soc.common.client.behaviour.IInteractionBehaviour#mouseEnter(soc.common.client.visuals.IPieceVisual, soc.common.client.visuals.board.IBoardVisual)
     */
    @Override
    public void mouseEnter(IPieceVisual pieceVisual, IBoardVisual board)
    {
        pieceVisual.setSelected(true);
    }

    /*
     * De-highlights hovered IPieceVisual
     * @see soc.common.client.behaviour.IInteractionBehaviour#mouseLeave(soc.common.client.visuals.IPieceVisual, soc.common.client.visuals.board.IBoardVisual)
     */
    @Override
    public void mouseOut(IPieceVisual pieceVisual, IBoardVisual board)
    {
        pieceVisual.setSelected(false);
    }

}
