package soc.gwtClient.visuals.behaviour.game;

import soc.common.actions.gameAction.GameAction;
import soc.gwtClient.visuals.abstractVisuals.GameBoardVisual;
import soc.gwtClient.visuals.abstractVisuals.PieceVisual;

public interface GameBehaviour
{
    public void start(GameBoardVisual gameVisual);

    public void setNeutral(GameBoardVisual visual);

    public void clicked(PieceVisual pieceVisual, GameBoardVisual gameBoardVisual);

    public void mouseEnter(PieceVisual pieceVisual,
            GameBoardVisual gameBoardVisual);

    public void mouseOut(PieceVisual pieceVisual,
            GameBoardVisual gameBoardVisual);

    public GameAction getGameAction();
}
