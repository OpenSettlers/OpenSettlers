package soc.common.views.behaviour.gameBoard;

import soc.common.views.widgetsInterface.visuals.GameBoardVisual;
import soc.common.views.widgetsInterface.visuals.SideVisual;
import soc.common.views.widgetsInterface.visuals.PieceVisual;

/*
 * Shows all possible sides for the user to select a side. 
 */
public abstract class BuildSideBehaviour implements GameBoardBehaviour
{

    @Override
    public void setNeutral(GameBoardVisual visual)
    {
        // /visual.getHexSidesVisual().setVisible(false);
    }

    @Override
    public void start(GameBoardVisual gameVisual)
    {
        // gameVisual.getHexSidesVisual().setVisible(true);
    }

    @Override
    public void mouseEnter(PieceVisual pieceVisual, GameBoardVisual board)
    {
        SideVisual sideVisual = pieceVisual.getSideVisual();
        if (sideVisual != null)
            pieceVisual.setSelected(true);
    }

    @Override
    public void mouseOut(PieceVisual pieceVisual, GameBoardVisual board)
    {
        SideVisual sideVisual = pieceVisual.getSideVisual();
        if (sideVisual != null)
            pieceVisual.setSelected(false);
    }
}
