package soc.gwtClient.visuals.svg;

import org.vaadin.gwtgraphics.client.Group;
import org.vaadin.gwtgraphics.client.animation.Animate;
import org.vaadin.gwtgraphics.client.shape.Circle;

import soc.common.board.HexPoint;
import soc.common.client.visuals.PieceVisual;
import soc.common.client.visuals.board.BoardVisual;
import soc.common.client.visuals.game.PointVisual;
import soc.gwtClient.game.Point2D;

public class SvgPointVisual extends PointVisual
{
    private Point2D location;
    private Group group;
    private Circle circle;

    public SvgPointVisual(BoardVisual parent, HexPoint hexPoint,
            Point2D location)
    {
        super(parent, hexPoint);

        this.location = location;

        this.group = new Group();
        circle = new Circle((int) location.getX(), (int) location.getY(),
                parent.getSize() / 2);
        group.add(circle);
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.client.visuals.PieceVisual#updateSelected()
     */
    @Override
    protected void updateSelected()
    {
        if (selected)
        {
            // Animate to grow 2x its size
            new Animate(circle, "radius", parent.getSize() / 2, parent
                    .getSize(), 500).start();
        }
        else
        {
            // Animate back to 1x its size
            new Animate(circle, "radius", parent.getSize(),
                    parent.getSize() / 2, 500).start();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.client.visuals.game.IPointVisual#addPieceVisual(soc.common
     * .client.visuals.PieceVisual)
     */
    @Override
    public void addPieceVisual(PieceVisual pieceVisual)
    {
        pieceVisuals.add(pieceVisual);
        group.add(((SvgVisual) pieceVisual).getVectorObject());
    }

}
