package soc.common.client.behaviour.game;

import soc.common.actions.gameAction.GameAction;
import soc.common.client.visuals.PieceVisual;
import soc.common.client.visuals.game.GameBoardVisual;

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
