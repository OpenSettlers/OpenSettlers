package soc.gwtClient.visuals.svg;

import org.vaadin.gwtgraphics.client.VectorObject;
import org.vaadin.gwtgraphics.client.shape.Rectangle;

import soc.common.board.pieces.Road;
import soc.gwtClient.game.Point2D;
import soc.gwtClient.visuals.abstractVisuals.AbstractRoadVisual;

public class RoadSvg extends AbstractRoadVisual implements SvgVisual
{
    GameBoardSvg parent;
    Rectangle rectangle;
    int normalStrokeWidth = 2;
    int selectedStrokeWidth = 5;

    public RoadSvg(Road road, GameBoardSvg parent)
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
        rectangle.setFillColor(parent.getGame().getCurrentTurn().getPlayer()
                .getColor());

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
     * soc.gwtClient.visuals.abstractVisuals.AbstractPieceVisual#updateSelected
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
