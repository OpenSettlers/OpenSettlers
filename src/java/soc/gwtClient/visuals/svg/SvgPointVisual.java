package soc.gwtClient.visuals.svg;

import org.vaadin.gwtgraphics.client.Group;
import org.vaadin.gwtgraphics.client.animation.Animate;
import org.vaadin.gwtgraphics.client.shape.Circle;

import soc.common.board.HexPoint;
import soc.common.board.hexes.Hex;
import soc.common.client.visuals.game.PointVisual;
import soc.gwtClient.game.Point2D;

public class SvgPointVisual extends PointVisual
{
    private Point2D location;
    private Group group;
    private Circle circle;
    
    public SvgPointVisual(HexPoint hexPoint, Point2D location)
    {
        super(hexPoint);
        
        this.location=location;
        
        this.group = new Group();
        circle = new Circle((int)location.getX(), (int)location.getY(), Hex.getSize()/2);
        group.add(circle);
    }

    /* (non-Javadoc)
     * @see soc.common.client.visuals.PieceVisual#updateSelected()
     */
    @Override
    protected void updateSelected()
    {
        if (selected)
        {
            // Animate to grow 2x its size
            new Animate(circle, "radius", Hex.getSize()/2, Hex.getSize(), 500).start();
        }
        else
        {
            // Animate back to 1x its size
            new Animate(circle, "radius", Hex.getSize(), Hex.getSize()/2, 500).start();            
        }
    }
    
}
