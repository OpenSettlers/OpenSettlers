package soc.common.client.behaviour.game;

import soc.common.client.visuals.PieceVisual;
import soc.common.client.visuals.board.BoardVisual;

public class BuildShipBehaviour extends BuildSideBehaviour
{
    /* (non-Javadoc)
     * @see soc.common.client.behaviour.game.BuildSideBehaviour#clicked(soc.common.client.visuals.IPieceVisual, soc.common.client.visuals.board.IBoardVisual)
     */
    @Override
    public void clicked(PieceVisual pieceVisual, BoardVisual board)
    {
        super.clicked(pieceVisual, board);
    }
}
