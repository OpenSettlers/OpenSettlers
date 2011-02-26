package soc.common.views.behaviour.gameBoard;

import soc.common.actions.gameAction.GameAction;
import soc.common.annotations.SeaFarers;
import soc.common.views.widgetsInterface.visuals.GameBoardVisual;
import soc.common.views.widgetsInterface.visuals.PieceVisual;

/*
 * TODO: implement
 */
@SeaFarers
public class BuildShipBehaviour extends BuildSideBehaviour
{
    @Override
    public GameAction getGameAction()
    {
        return null;
    }

    @Override
    public void clicked(PieceVisual pieceVisual, GameBoardVisual gameBoardVisual)
    {
    }
}
