package soc.gwtClient.visuals.svg;

import org.vaadin.gwtgraphics.client.Group;
import org.vaadin.gwtgraphics.client.shape.Circle;
import org.vaadin.gwtgraphics.client.shape.Path;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;

import soc.common.board.hexes.Hex;
import soc.common.board.ports.Port;
import soc.common.board.ports.TwoToOneResourcePort;
import soc.common.client.visuals.board.IBoardVisual;
import soc.common.client.visuals.board.IPortVisual;
import soc.common.client.visuals.board.PortVisual;
import soc.gwtClient.game.Point2D;

public class SvgPortVisual extends PortVisual 
    implements MouseMoveHandler, ClickHandler, MouseOutHandler
{
    private final double fillOpacity = 0.5;
    private Group group = new Group();
    private Path territoryPath;
    private Circle circle;
    private Point2D point;
    
    /* (non-Javadoc)
     * @see soc.common.client.visuals.board.PortVisual#updateSelected()
     */
    @Override
    protected void updateSelected()
    {
        if (selected)
        {
            territoryPath.setFillOpacity(0.8);
        }
        else
        {
            territoryPath.setFillOpacity(fillOpacity);
        }
    }

    /**
     * @return the group
     */
    public Group getGroup()
    {
        return group;
    }
    
    public SvgPortVisual(Port port, IBoardVisual parent, Point2D point)
    {
        super(port, parent);
        
        this.point=point;
        
        this.group=new Group();
        this.territoryPath = new Path(point.getX(), point.getY());
        if (port != null)
        {
            createPath();
        }
        territoryPath.setStrokeOpacity(0.2);
        territoryPath.setFillOpacity(fillOpacity);
        
        circle = new Circle(point.getX(), point.getY(), Hex.getSize()/3);
        String color = "White";
        if (port instanceof TwoToOneResourcePort)
        {
            TwoToOneResourcePort twoToOneResourcePort = (TwoToOneResourcePort) port;
            color = twoToOneResourcePort.getResource().getColor();
        }
        circle.setFillColor(color);
        circle.setFillOpacity(fillOpacity);
        circle.setStrokeWidth(0);
        
        group.addMouseMoveHandler(this);
        group.addMouseOutHandler(this);
        group.addClickHandler(this);
        group.add(territoryPath);
        group.add(circle);
    }

    /* (non-Javadoc)
     * @see soc.common.client.visuals.board.PortVisual#updatePort()
     */
    @Override
    protected void updatePort()
    {
        if (port != null)
        {
            group.remove(territoryPath);
            
            territoryPath = new Path(point.getX(), point.getY());
            territoryPath.setStrokeOpacity(0.2);
            territoryPath.setFillOpacity(fillOpacity);

            // Remove old stuff when it's there still
            //for (int i=0; i< territoryPath.getStepCount(); i++)
            {
            //    territoryPath.removeStep(0);
            }
            
            // Update the path to reflect current port
            createPath();
            
            group.add(territoryPath);
            
            // Make sure the new port is visible
            setVisible(true);
        }
        else
        {
            setVisible(false);
        }
    }
    
    /*
     * Creates a port using a path svg element
     */
    private void createPath()
    {
        territoryPath.moveTo(point.getX(), point.getY());
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
            territoryPath.lineRelativelyTo(-(int)Hex.getHalfWidth(), -(int)Hex.getSize()/2);
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
        territoryPath.close();
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

    /* (non-Javadoc)
     * @see soc.common.client.visuals.board.PortVisual#updateVisible()
     */
    @Override
    protected void updateVisible()
    {
        group.setVisible(visible);
    }

}
