package soc.gwtClient.game.widgetsSvg.visuals;

import org.vaadin.gwtgraphics.client.Group;
import org.vaadin.gwtgraphics.client.VectorObject;

import soc.common.board.HexLocation;
import soc.common.board.ports.Port;
import soc.gwtClient.game.Point2D;
import soc.gwtClient.game.widgetsAbstract.visuals.AbstractPortPossibilitiesVisual;

public class PortPossibilitiesSvg extends AbstractPortPossibilitiesVisual
        implements SvgVisual
{
    private Group group;

    public PortPossibilitiesSvg(HexLocation seaLocation,
            BoardSvg parent)
    {
        super(seaLocation, parent);

        group = new Group();

        Point2D coordinates = parent.calculatePosition(seaLocation);
        Point2D centeredCoordinates = new Point2D((int) coordinates.getX(),
                (int) (coordinates.getY() + parent.getHalfHeight()));

        for (Port port : ports)
        {
            PortSvg portVisual = new PortSvg(port, parent,
                    centeredCoordinates);
            group.add(portVisual.getGroup());
            portVisuals.add(portVisual);
        }
    }

    private BoardSvg getSvgParent()
    {
        return (BoardSvg) parent;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.client.visuals.board.PortPossibilitiesVisual#updateVisible()
     */
    @Override
    protected void updateVisible()
    {
        group.setVisible(visible);
    }

    public void resizeAndReposition(Point2D newPoint)
    {
        group.clear();
        Point2D coordinates = getSvgParent().calculatePosition(hexLocation);
        Point2D centeredCoordinates = new Point2D((int) coordinates.getX(),
                (int) (coordinates.getY() + parent.getHalfHeight()));

        for (Port port : ports)
        {
            PortSvg portVisual = new PortSvg(port, parent,
                    centeredCoordinates);
            group.add(portVisual.getGroup());
            portVisuals.add(portVisual);
        }
    }

    @Override
    public VectorObject getVectorObject()
    {
        return group;
    }
}
