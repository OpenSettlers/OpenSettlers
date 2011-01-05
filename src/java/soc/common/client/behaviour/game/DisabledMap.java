package soc.common.client.behaviour.game;

import soc.common.actions.gameAction.GameAction;
import soc.common.client.visuals.PieceVisual;
import soc.common.client.visuals.board.HexVisual;
import soc.common.client.visuals.game.GameBoardVisual;

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
