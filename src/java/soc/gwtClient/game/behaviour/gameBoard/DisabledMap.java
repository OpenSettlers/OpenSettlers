package soc.gwtClient.game.behaviour.gameBoard;

import soc.common.actions.gameAction.GameAction;
import soc.gwtClient.game.widgetsInterface.visuals.GameBoardVisual;
import soc.gwtClient.game.widgetsInterface.visuals.PieceVisual;

public class DisabledMap implements GameBoardBehaviour
{

    @Override
    public void clicked(PieceVisual pieceVisual, GameBoardVisual gameBoardVisual)
    {
    }

    @Override
    public void mouseEnter(PieceVisual pieceVisual,
            GameBoardVisual gameBoardVisual)
    {
    }

    @Override
    public void mouseOut(PieceVisual pieceVisual,
            GameBoardVisual gameBoardVisual)
    {
    }

    @Override
    public void setNeutral(GameBoardVisual gameVisual)
    {
        // for (HexVisual hexVisual : gameVisual.getHexVisuals().values())
        // hexVisual.setDarkened(false);
    }

    @Override
    public void start(GameBoardVisual gameVisual)
    {
        // for (HexVisual hexVisual : gameVisual.getHexVisuals().values())
        // hexVisual.setDarkened(true);
    }

    @Override
    public GameAction getGameAction()
    {
        return null;
    }

}
