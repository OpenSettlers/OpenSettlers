package soc.gwtClient.visuals.behaviour.game;

import soc.common.actions.gameAction.GameAction;
import soc.gwtClient.visuals.abstractVisuals.GameBoardVisual;
import soc.gwtClient.visuals.abstractVisuals.HexVisual;
import soc.gwtClient.visuals.abstractVisuals.PieceVisual;

public class DisabledMap implements GameBehaviour
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
        for (HexVisual hexVisual : gameVisual.getHexVisuals().values())
            hexVisual.setDarkened(false);
    }

    @Override
    public void start(GameBoardVisual gameVisual)
    {
        for (HexVisual hexVisual : gameVisual.getHexVisuals().values())
            hexVisual.setDarkened(true);
    }

    @Override
    public GameAction getGameAction()
    {
        return null;
    }

}
