package soc.common.client.visuals.board;

import java.util.HashMap;

import soc.common.board.Board;
import soc.common.board.hexes.Hex;
import soc.common.client.behaviour.DefaultBehaviour;
import soc.common.client.behaviour.InteractionBehaviour;
import soc.common.client.visuals.AbstractPieceVisual;
import soc.gwtClient.editor.BehaviourChanged;
import soc.gwtClient.editor.IBehaviourChangedHandler;

public abstract class AbstractBoardVisual extends AbstractPieceVisual implements
        BoardVisual, IBehaviourChangedHandler
{
    protected InteractionBehaviour editBehaviour = new DefaultBehaviour();
    protected Board board;
    protected HashMap<Hex, HexVisual> hexVisuals = new HashMap<Hex, HexVisual>();

    protected double sideLength = 35;
    private double h;
    private double halfWidth;
    private double b;
    private double width;

    /**
     * @return the hexVisuals
     */
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
    public InteractionBehaviour getCurrentBehaviour()
    {
        return editBehaviour;
    }

    @Override
    public BoardVisual setInteractionBehaviour(InteractionBehaviour behaviour)
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
        setInteractionBehaviour(behaviourChanged.getBehaviour());
    }

    @Override
    public void hideTerritories()
    {
        for (HexVisual hexVisual : hexVisuals.values())
        {
            hexVisual.getTerritory().setVisible(false);
        }
    }

    @Override
    public void showTerritories()
    {
        for (HexVisual hexVisual : hexVisuals.values())
        {
            hexVisual.getTerritory().setVisible(true);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.client.visuals.board.IBoardVisual#getBottomHeight()
     */
    @Override
    public double getBottomHeight()
    {
        return h;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.client.visuals.board.IBoardVisual#getHalfHeight()
     */
    @Override
    public double getHalfHeight()
    {
        return b / 2;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.client.visuals.board.IBoardVisual#getHalfWidth()
     */
    @Override
    public double getHalfWidth()
    {
        return halfWidth;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.client.visuals.board.IBoardVisual#getHeight()
     */
    @Override
    public double getHeight()
    {
        return b;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.client.visuals.board.IBoardVisual#getPartialHeight()
     */
    @Override
    public double getPartialHeight()
    {
        return sideLength + h;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.client.visuals.board.IBoardVisual#getSize()
     */
    @Override
    public int getSize()
    {
        return (int) sideLength;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.client.visuals.board.IBoardVisual#getWidth()
     */
    @Override
    public double getWidth()
    {
        return width;
    }

    // / <summary>
    // / Helper function for size calculation
    // / </summary>
    // / @param degrees
    protected double DegreesToRadians(double degrees)
    {
        return degrees * Math.PI / 180;
    }

    protected void calculateHexSizes()
    {
        // TODO: come up with descriptive names for h and b
        h = Math.sin(DegreesToRadians(30)) * sideLength;
        halfWidth = Math.cos(DegreesToRadians(30)) * sideLength;
        b = sideLength + 2 * h;
        width = 2 * halfWidth;
    }
}
