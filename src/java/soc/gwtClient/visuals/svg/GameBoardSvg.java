package soc.gwtClient.visuals.svg;

import java.util.HashMap;

import soc.common.board.hexes.Hex;
import soc.common.board.pieces.PiecesChangedEvent;
import soc.common.board.routing.GraphPoint;
import soc.common.board.routing.GraphSide;
import soc.common.game.Game;
import soc.gwtClient.visuals.abstractVisuals.AbstractGameBoardVisual;
import soc.gwtClient.visuals.abstractVisuals.HexVisual;
import soc.gwtClient.visuals.abstractVisuals.PieceVisual;
import soc.gwtClient.visuals.abstractVisuals.PointVisual;
import soc.gwtClient.visuals.abstractVisuals.SideVisual;
import soc.gwtClient.visuals.abstractVisuals.VisualFactory;
import soc.gwtClient.visuals.behaviour.NoBehaviour;

import com.google.gwt.user.client.ui.Widget;

public class GameBoardSvg extends AbstractGameBoardVisual
{
    BoardSvg boardSvg;

    /**
     * @return the boardSvg
     */
    public BoardSvg getBoardSvg()
    {
        return boardSvg;
    }

    public GameBoardSvg(Game game, int width, int height)
    {
        super(game);

        boardSvg = new BoardSvg(width, height, game.getBoard());
        boardSvg.setBoardBehaviour(new NoBehaviour());

        for (GraphSide side : board.getGraph().getSides())
        {
            SideVisual sideVisual = visualFactory.createSideVisual(side);
            sideVisuals.put(side, sideVisual);
            boardSvg.getDrawingArea().add(
                    ((SvgVisual) sideVisual).getVectorObject());
        }

        for (GraphPoint point : board.getGraph().getPoints())
        {
            PointVisual pointVisual = visualFactory.createPointVisual(point);
            pointVisuals.put(point, pointVisual);
            boardSvg.getDrawingArea().add(
                    ((SvgVisual) pointVisual).getVectorObject());
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.client.visuals.board.AbstractBoardVisual#getHexVisuals()
     */
    @Override
    public HashMap<Hex, HexVisual> getHexVisuals()
    {
        return boardSvg.getHexVisuals();
    }

    @Override
    public Widget asWidget()
    {
        return boardSvg.getDrawingArea();
    }

    @Override
    public VisualFactory createVisualFactory()
    {
        return new SvgVisualFactory(this);
    }

    @Override
    public void onPiecesChanged(PiecesChangedEvent list)
    {
        if (list.getAddedPiece() != null)
        {
            // Create a new visual for the added player piece
            PieceVisual newPieceVisual = visualFactory
                    .createPlayerPieceVisual(list.getAddedPiece());

            // Keep track of it
            playerPieceVisuals.put(list.getAddedPiece(), newPieceVisual);

            // Add to the svg canvas
            boardSvg.getDrawingArea().add(
                    ((SvgVisual) newPieceVisual).getVectorObject());
        }
    }
}
