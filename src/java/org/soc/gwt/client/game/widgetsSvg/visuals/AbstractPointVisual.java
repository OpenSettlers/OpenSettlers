package org.soc.gwt.client.game.widgetsSvg.visuals;

import java.util.ArrayList;
import java.util.List;

import org.soc.common.board.layout.HexPoint;
import org.soc.common.views.widgetsInterface.visuals.GameBoardVisual;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual;
import org.soc.common.views.widgetsInterface.visuals.PointVisual;
import org.soc.gwt.client.game.widgetsAbstract.visuals.AbstractPieceVisual;


public abstract class AbstractPointVisual extends AbstractPieceVisual implements
        PointVisual
{
    protected HexPoint hexPoint;
    protected GameBoardVisual parent;
    protected List<PieceVisual> pieceVisuals = new ArrayList<PieceVisual>();

    public AbstractPointVisual(GameBoardVisual parent, HexPoint hexPoint)
    {
        super();
        this.parent = parent;
        this.hexPoint = hexPoint;
    }

    /**
     * @return the hexPoint
     */
    public HexPoint getHexPoint()
    {
        return hexPoint;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.gwt.client.game.widgetsAbstract.visuals.AbstractPieceVisual#getPointVisual
     * ()
     */
    @Override
    public PointVisual getPointVisual()
    {
        return this;
    }

}
