package org.soc.gwt.client.game.widgetsSvg.visuals;

import org.soc.common.board.layout.HexLocation;
import org.soc.common.views.widgetsInterface.visuals.BoardVisual;
import org.soc.common.views.widgetsInterface.visuals.PortPossibilitiesVisual;
import org.soc.gwt.client.game.widgetsAbstract.visuals.AbstractPieceVisual;
import org.vaadin.gwtgraphics.client.VectorObject;
import org.vaadin.gwtgraphics.client.shape.Circle;


public class DummyPortPossibilitiesSvg extends AbstractPieceVisual implements
        PortPossibilitiesVisual, SvgVisual
{

    public DummyPortPossibilitiesSvg(HexLocation hexLocation, BoardVisual parent)
    {
    }

    @Override
    public VectorObject getVectorObject()
    {
        return new Circle(1, 1, 1);
    }

    @Override
    public void updatePossibilities()
    {
        // TODO Auto-generated method stub

    }

}
