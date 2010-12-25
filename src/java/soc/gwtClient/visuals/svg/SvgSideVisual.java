package soc.gwtClient.visuals.svg;

import org.vaadin.gwtgraphics.client.Group;
import org.vaadin.gwtgraphics.client.animation.Animate;
import org.vaadin.gwtgraphics.client.shape.Rectangle;

import soc.common.board.HexSide;
import soc.common.board.hexes.AbstractHex;
import soc.common.client.visuals.game.SideVisual;
import soc.gwtClient.game.Point2D;

/*
 * Represents an HexSide drawn using SVG web technology
 */
public class SvgSideVisual extends SideVisual
{
    private Point2D location;
    private Group group;
    private Rectangle rectangle;
    private double width = 0.0;
    private double height = 0.0;

    public SvgSideVisual(HexSide hexSide, Point2D location)
    {
        super(hexSide);
        this.location = location;

        width = AbstractHex.getSize() * 0.8;
        height = AbstractHex.getSize() * 0.3;

        group = new Group();
        rectangle = new Rectangle((int) location.getX(), (int) location.getY(),
                (int) width, (int) height);

        int degrees = 0;
        switch (hexSide.getDirection())
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

        group.add(rectangle);
    }

    /*
     * Enlarges the hexSide in animated form when selected
     * 
     * @see soc.common.client.visuals.PieceVisual#updateSelected()
     */
    @Override
    protected void updateSelected()
    {
        if (selected)
        {
            new Animate(rectangle, "width", width, width * 1.25, 500).start();
            new Animate(rectangle, "height", height, height * 1.25, 500)
                    .start();
        }
        else
        {
            new Animate(rectangle, "width", width * 1.25, width, 500).start();
            new Animate(rectangle, "height", height * 1.25, height, 500)
                    .start();
        }
    }

}
