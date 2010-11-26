package soc.gwtClient.client.visuals.svg;

import org.vaadin.gwtgraphics.client.Group;
import org.vaadin.gwtgraphics.client.shape.Path;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;

import soc.common.board.hexes.Hex;
import soc.common.board.ports.Port;
import soc.common.client.visuals.board.IBoardVisual;
import soc.common.client.visuals.board.PortVisual;
import soc.gwtClient.client.Point2D;

public class SvgPortVisual extends PortVisual 
    implements MouseMoveHandler, ClickHandler, MouseOutHandler
{
    Group group = new Group();
    Path territoryPath;
    /**
     * @return the group
     */
    public Group getGroup()
    {
        return group;
    }

    Point2D point;
    
    public SvgPortVisual(Port port, IBoardVisual parent, Point2D point)
    {
        super(port, parent);
        
        this.point=point;
        
        group=new Group();
        territoryPath = new Path(point.getX(), point.getY());
        if (port != null)
        {
            createPath();
        }
        
        group.addMouseMoveHandler(this);
        group.addMouseOutHandler(this);
        group.addClickHandler(this);
        group.add(territoryPath);
    }

    /* (non-Javadoc)
     * @see soc.common.client.visuals.board.PortVisual#updatePort()
     */
    @Override
    protected void updatePort()
    {
        
    }
    
    private void createPath()
    {
        switch (port.getRotationPosition())
        {
        case DEG0:
            territoryPath.lineRelativelyTo(0, -(int)Hex.getHalfHeight());
            territoryPath.lineRelativelyTo((int)Hex.getHalfWidth(), (int)Hex.getBottomHeight());
            territoryPath.lineRelativelyTo(-(int)Hex.getHalfWidth(), (int)Hex.getSize()/2);
            break;
        case DEG60:
            territoryPath.lineRelativelyTo((int)Hex.getHalfWidth(), -(int)Hex.getSize()/2);
            territoryPath.lineRelativelyTo(0, (int)Hex.getSize());
            territoryPath.lineRelativelyTo(-(int)Hex.getHalfWidth(), (int)Hex.getSize()/2);
            break;
        case DEG120:
            territoryPath.lineRelativelyTo((int)Hex.getHalfWidth(), (int)Hex.getSize()/2);
            territoryPath.lineRelativelyTo(-(int)Hex.getHalfWidth(), (int)Hex.getBottomHeight());
            territoryPath.lineRelativelyTo(0, -(int)Hex.getHalfHeight());
            break;
        case DEG180:
            territoryPath.lineRelativelyTo(0, (int)Hex.getHalfHeight());
            territoryPath.lineRelativelyTo(-(int)Hex.getHalfWidth(), -(int)Hex.getBottomHeight());
            territoryPath.lineRelativelyTo((int)Hex.getHalfWidth(), -(int)Hex.getSize()/2);
            break;
        case DEG240:
            territoryPath.lineRelativelyTo(-(int)Hex.getHalfWidth(), (int)Hex.getSize()/2);
            territoryPath.lineRelativelyTo(0, -(int)Hex.getSize());
            territoryPath.lineRelativelyTo((int)Hex.getHalfWidth(), (int)Hex.getSize()/2);
            break;
        case DEG300:
            territoryPath.lineRelativelyTo(-(int)Hex.getHalfWidth(), -(int)Hex.getSize()/2);
            territoryPath.lineRelativelyTo((int)Hex.getHalfWidth(), -(int)Hex.getBottomHeight());
            territoryPath.lineRelativelyTo(0, (int)Hex.getHalfHeight());
            break;
        }
    }

    /* (non-Javadoc)
     * @see soc.common.client.visuals.board.PortVisual#updateValid()
     */
    @Override
    protected void updateValid()
    {
        if (valid)
        {
            territoryPath.setFillColor("Red");
        }
        else
        {
            territoryPath.setFillColor("White");
        }
    }

    @Override
    public void onMouseMove(MouseMoveEvent event)
    {
        parent.getInteractionBehaviour().mouseEnter(this, parent);
    }

    @Override
    public void onClick(ClickEvent event)
    {
        parent.getInteractionBehaviour().clicked(this, parent);
    }

    @Override
    public void onMouseOut(MouseOutEvent event)
    {
        parent.getInteractionBehaviour().mouseOut(this, parent);
    }
}
