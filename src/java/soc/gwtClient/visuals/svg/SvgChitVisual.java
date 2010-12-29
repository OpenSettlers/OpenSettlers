package soc.gwtClient.visuals.svg;

import org.vaadin.gwtgraphics.client.Image;

import soc.common.board.Chit;
import soc.common.client.visuals.board.ChitVisual;
import soc.common.client.visuals.board.IBoardVisual;
import soc.gwtClient.game.Point2D;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;

public class SvgChitVisual extends ChitVisual implements MouseMoveHandler,
        MouseOutHandler, ClickHandler
{
    Image chitImage;

    /**
     * @return the territoryImage
     */
    public Image getTerritoryImage()
    {
        return chitImage;
    }

    Point2D point;

    public SvgChitVisual(Chit chit, IBoardVisual parent, Point2D point)
    {
        super(chit, parent);
        this.point = point;
        int width = (int) (parent.getSize() * 0.66);
        int height = (int) (parent.getSize() * 0.66);
        this.chitImage = new Image(point.getX() - (width / 2), point.getY()
                - (height / 2), width, height, "");
        updateChit();

        chitImage.addMouseMoveHandler(this);
        chitImage.addMouseOutHandler(this);
        chitImage.addClickHandler(this);
    }

    @Override
    protected void updateChit()
    {
        String img = "";

        if (chit != null)
        {
            // New chit is not null, so the user wants to set a new typ of chit
            // on the hex
            if (chit.getNumber() != 0)
            {
                // User changed chit to 2,3,4,5,6,8,9,10,11,12
                img = "images/chit" + chit.getNumber() + ".png";
            }
            else
            {
                // When the number is 0, a randomchit will be assigned
                img = "images/chitrandom.png";
            }
        }

        // Add the new chit to the group of chits
        chitImage.setHref(img);
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.client.visuals.board.ChitVisual#updateVisible()
     */
    @Override
    protected void updateVisible()
    {
        chitImage.setVisible(visible);
    }

    @Override
    public void onMouseMove(MouseMoveEvent event)
    {
        parent.getCurrentBehaviour().mouseEnter(this, parent);
    }

    @Override
    public void onMouseOut(MouseOutEvent event)
    {
        parent.getCurrentBehaviour().mouseOut(this, parent);
    }

    @Override
    public void onClick(ClickEvent event)
    {
        parent.getCurrentBehaviour().clicked(this, parent);
    }

    public void resizeAndReposition(Point2D newPoint)
    {
        this.point = newPoint;
        int width = (int) (parent.getSize() * 0.66);
        int height = (int) (parent.getSize() * 0.66);
        this.chitImage.setX(newPoint.getX() - (width / 2));
        this.chitImage.setY(newPoint.getY() - (height / 2));
        this.chitImage.setHeight(height);
        this.chitImage.setWidth(width);
    }

}
