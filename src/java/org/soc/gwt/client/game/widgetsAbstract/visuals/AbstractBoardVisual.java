package org.soc.gwt.client.game.widgetsAbstract.visuals;

import java.util.HashMap;

import org.soc.common.board.Board;
import org.soc.common.board.hexes.Hex;
import org.soc.common.views.behaviour.board.BoardBehaviour;
import org.soc.common.views.behaviour.board.DefaultBehaviour;
import org.soc.common.views.widgetsInterface.visuals.BoardVisual;
import org.soc.common.views.widgetsInterface.visuals.HexVisual;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual;
import org.soc.gwt.client.editor.BehaviourChanged;
import org.soc.gwt.client.editor.BehaviourChangedHandler;


/*
 * Abstracts
 */
public abstract class AbstractBoardVisual extends AbstractPieceVisual implements
                BoardVisual, BehaviourChangedHandler
{
    protected BoardBehaviour editBehaviour = new DefaultBehaviour();
    protected Board board;
    protected HashMap<Hex, HexVisual> hexVisuals = new HashMap<Hex, HexVisual>();

    protected double sideLength = 35;
    private double h;
    private double halfWidth;
    private double height;
    private double width;

    @Override
    public void clicked(PieceVisual pieceVisual, BoardVisual board)
    {
        editBehaviour.clicked(pieceVisual, board);
    }

    @Override
    public void mouseEnter(PieceVisual pieceVisual, BoardVisual board)
    {
        editBehaviour.mouseEnter(pieceVisual, board);
    }

    @Override
    public void mouseOut(PieceVisual pieceVisual, BoardVisual board)
    {
        editBehaviour.mouseOut(pieceVisual, board);
    }

    /** @return the hexVisuals */
    public HashMap<Hex, HexVisual> getHexVisuals()
    {
        return hexVisuals;
    }

    public AbstractBoardVisual()
    {
        calculateHexSizes();
    }

    @Override
    public Board getBoard()
    {
        return board;
    }

    @Override
    public BoardBehaviour getBoardBehaviour()
    {
        return editBehaviour;
    }

    @Override
    public BoardVisual setBoardBehaviour(BoardBehaviour behaviour)
    {
        editBehaviour = behaviour;

        updateBehaviour();

        return this;
    }

    private void updateBehaviour()
    {

    }

    @Override
    public void onBehaviourChanged(BehaviourChanged behaviourChanged)
    {
        setBoardBehaviour(behaviourChanged.getBehaviour());
    }

    @Override
    public void hideTerritories()
    {
        for (HexVisual hexVisual : hexVisuals.values())
            hexVisual.getTerritory().setVisible(false);
    }

    @Override
    public void showTerritories()
    {
        for (HexVisual hexVisual : hexVisuals.values())
            hexVisual.getTerritory().setVisible(true);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.client.visuals.board.IBoardVisual#getBottomHeight()
     */
    @Override
    public double getBottomHeight()
    {
        return h;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.client.visuals.board.IBoardVisual#getHalfHeight()
     */
    @Override
    public double getHalfHeight()
    {
        return height / 2;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.client.visuals.board.IBoardVisual#getHalfWidth()
     */
    @Override
    public double getHalfWidth()
    {
        return halfWidth;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.client.visuals.board.IBoardVisual#getHeight()
     */
    @Override
    public double getHeight()
    {
        return height;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.client.visuals.board.IBoardVisual#getPartialHeight()
     */
    @Override
    public double getPartialHeight()
    {
        return sideLength + h;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.client.visuals.board.IBoardVisual#getSize()
     */
    @Override
    public int getSize()
    {
        return (int) sideLength;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.client.visuals.board.IBoardVisual#getWidth()
     */
    @Override
    public double getHexagonWidth()
    {
        return width;
    }

    // / <summary>
    // / Helper function for size calculation
    // / </summary>
    // / @param degrees
    protected double degreesToRadians(double degrees)
    {
        return degrees * Math.PI / 180;
    }

    protected void calculateHexSizes()
    {
        // TODO: come up with descriptive name for "h"
        h = Math.sin(degreesToRadians(30)) * sideLength;
        halfWidth = Math.cos(degreesToRadians(30)) * sideLength;
        height = sideLength + (2 * h);
        width = 2 * halfWidth;
    }
}
