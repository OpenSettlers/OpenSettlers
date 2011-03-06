package soc.gwtClient.game.widgetsSvg.visuals;

import org.vaadin.gwtgraphics.client.Image;
import org.vaadin.gwtgraphics.client.VectorObject;

import soc.common.board.chits.Chit;
import soc.common.views.widgetsInterface.generic.Point2D;
import soc.common.views.widgetsInterface.visuals.BoardVisual;
import soc.gwtClient.game.widgetsAbstract.visuals.AbstractChitVisual;
import soc.gwtClient.images.Resources;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;

public class ChitSvg extends AbstractChitVisual implements MouseMoveHandler,
                MouseOutHandler, ClickHandler, SvgVisual
{
    Image chitImage;
    Point2D point;

    /** @return the territoryImage */
    public Image getTerritoryImage()
    {
        return chitImage;
    }

    public ChitSvg(Chit chit, BoardVisual parent, Point2D point)
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
            img = Resources.chit(chit, 32).getURL();
        }

        // Add the new chit to the group of chits
        chitImage.setHref(img);
        chitImage.setVisible(chit != null);
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
        parent.mouseEnter(this, parent);
    }

    @Override
    public void onMouseOut(MouseOutEvent event)
    {
        parent.mouseOut(this, parent);
    }

    @Override
    public void onClick(ClickEvent event)
    {
        parent.clicked(this, parent);
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

    @Override
    public VectorObject getVectorObject()
    {
        return chitImage;
    }
}
