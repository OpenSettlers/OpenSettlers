package soc.gwtClient.game.behaviour.board;

import soc.gwtClient.game.widgetsInterface.visuals.BoardVisual;
import soc.gwtClient.game.widgetsInterface.visuals.GameBoardVisual;
import soc.gwtClient.game.widgetsInterface.visuals.PieceVisual;

/*
 * Proxies boardbehaviour through to GameBoardBaheviour of encapsulated GameBoardVisual
 */
public class ProxyBehaviour implements BoardBehaviour
{
    private GameBoardVisual gameBoardVisual;

    public ProxyBehaviour(GameBoardVisual gameBoardVisual)
    {
        super();
        this.gameBoardVisual = gameBoardVisual;
    }

    @Override
    public void clicked(PieceVisual pieceVisual, BoardVisual board)
    {
        gameBoardVisual.clicked(pieceVisual, gameBoardVisual);
    }

    @Override
    public void mouseEnter(PieceVisual pieceVisual, BoardVisual board)
    {
        gameBoardVisual.mouseEnter(pieceVisual, gameBoardVisual);
    }

    @Override
    public void mouseOut(PieceVisual pieceVisual, BoardVisual board)
    {
        gameBoardVisual.mouseOut(pieceVisual, gameBoardVisual);
    }
}