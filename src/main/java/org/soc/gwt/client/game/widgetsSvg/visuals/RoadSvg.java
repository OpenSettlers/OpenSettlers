package org.soc.gwt.client.game.widgetsSvg.visuals;

import org.soc.common.game.pieces.Road;
import org.soc.common.views.widgetsInterface.generic.Point2D;
import org.soc.gwt.client.game.widgetsAbstract.visuals.AbstractRoadVisual;
import org.vaadin.gwtgraphics.client.VectorObject;
import org.vaadin.gwtgraphics.client.shape.Rectangle;


public class RoadSvg extends AbstractRoadVisual implements SvgVisual
{
    GameBoardSvg parent;
    Rectangle rectangle;
    int normalStrokeWidth = 2;
    int selectedStrokeWidth = 5;

    public RoadSvg(GameBoardSvg parent, Road road)
    {
        super(road);
        this.parent = parent;

        Point2D location = parent.getBoardSvg().calculatePosition(
                road.getSide());

        double width = parent.getSize() * 0.6;
        double height = parent.getSize() * 0.2;

        rectangle = new Rectangle((int) ((int) location.getX() - (width / 2)),
                (int) location.getY(), (int) width, (int) height);
        rectangle.setStrokeWidth(normalStrokeWidth);
        rectangle.setFillColor(parent.game().turn().player()
                .color());

        int degrees = 0;
        switch (road.getSide().getDirection())
        {
        case SLOPEDOWN:
            degrees = 30;
            break;
        case SLOPEUP:
            degrees = 330;
            break;
        case UPDOWN:
            degrees = 90;
            break;
        }

        rectangle.setRotation(degrees);
    }

    @Override
    public VectorObject getVectorObject()
    {
        return rectangle;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.gwt.client.visuals.abstractVisuals.AbstractPieceVisual#updateSelected
     * ()
     */
    @Override
    protected void updateSelected()
    {
        if (selected)
            rectangle.setStrokeWidth(selectedStrokeWidth);
        else
            rectangle.setStrokeWidth(normalStrokeWidth);
    }

}
