package soc.common.client.behaviour.game;

import soc.common.actions.gameAction.GameAction;
import soc.common.client.visuals.PieceVisual;
import soc.common.client.visuals.game.GameBoardVisual;

public class BuildShipBehaviour extends BuildSideBehaviour
{
    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.client.behaviour.game.BuildSideBehaviour#clicked(soc.common
     * .client.visuals.IPieceVisual,
     * soc.common.client.visuals.board.IBoardVisual)
     */
    @Override
    public void clicked(PieceVisual pieceVisual, GameBoardVisual board)
    {
        super.clicked(pieceVisual, board);
    }

    @Override
    public GameAction getGameAction()
    {
        // TODO Auto-generated method stub
        return null;
    }
}
