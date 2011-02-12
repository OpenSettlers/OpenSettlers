package soc.gwtClient.visuals.svg;

import org.vaadin.gwtgraphics.client.Group;
import org.vaadin.gwtgraphics.client.VectorObject;
import org.vaadin.gwtgraphics.client.animation.Animate;
import org.vaadin.gwtgraphics.client.shape.Rectangle;

import soc.common.board.HexSide;
import soc.gwtClient.game.Point2D;
import soc.gwtClient.visuals.abstractVisuals.SideVisual;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;

/*
 * Represents an HexSide drawn using SVG web technology
 */
public class SideSvg extends SideVisual implements SvgVisual,
        ClickHandler, MouseOutHandler, MouseMoveHandler
{
    private Point2D location;
    private Group group;
    private Rectangle rectangle;
    private double width = 0.0;
    private double height = 0.0;
    GameBoardSvg parent;

    public SideSvg(GameBoardSvg parent, HexSide hexSide)
    {
        super(hexSide);
        this.parent = parent;

        location = parent.getBoardSvg().calculatePosition(hexSide);

        width = parent.getSize() * 0.8;
        height = parent.getSize() * 0.3;

        group = new Group();
        rectangle = new Rectangle((int) ((int) location.getX() - (width / 2)),
                (int) location.getY(), (int) width, (int) height);
        rectangle.setStrokeWidth(0);
        rectangle.setFillColor(parent.getGame().getCurrentTurn().getPlayer()
                .getColor());

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

        group.addClickHandler(this);
        group.addMouseOutHandler(this);
        group.addMouseMoveHandler(this);

        // default on not showing the thing
        setVisible(false);
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

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.client.visuals.AbstractPieceVisual#updateVisible()
     */
    @Override
    protected void updateVisible()
    {
        group.setVisible(visible);
    }

    @Override
    public VectorObject getVectorObject()
    {
        return group;
    }

    @Override
    public void onMouseMove(MouseMoveEvent event)
    {
        parent.getBehaviour().mouseEnter(this, parent);
    }

    @Override
    public void onMouseOut(MouseOutEvent event)
    {
        parent.getBehaviour().mouseOut(this, parent);
    }

    @Override
    public void onClick(ClickEvent event)
    {
        parent.getBehaviour().clicked(this, parent);
    }

}
