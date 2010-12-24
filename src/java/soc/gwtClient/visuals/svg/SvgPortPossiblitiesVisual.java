package soc.gwtClient.visuals.svg;

import org.vaadin.gwtgraphics.client.Group;

import soc.common.board.HexLocation;
import soc.common.board.hexes.AbstractHex;
import soc.common.board.ports.Port;
import soc.common.client.visuals.board.PortPossibilitiesVisual;
import soc.gwtClient.game.Point2D;

public class SvgPortPossiblitiesVisual extends PortPossibilitiesVisual
{
    /**
     * @return the group
     */
    public Group getGroup()
    {
        return group;
    }

    private Group group;
    
    public SvgPortPossiblitiesVisual(HexLocation seaLocation, SvgBoardVisual parent)
    {
        super(seaLocation, parent);
                
        group = new Group();
        
        Point2D coordinates = parent.calculatePosition(seaLocation);
        Point2D centeredCoordinates = new Point2D((int)coordinates.getX() , (int)(coordinates.getY()+ AbstractHex.getHalfHeight()));
        
        for (Port port : ports)
        {
            SvgPortVisual portVisual = new SvgPortVisual(port, parent, centeredCoordinates);
            group.add(portVisual.getGroup());
            portVisuals.add(portVisual);
        }
    }

    /* (non-Javadoc)
     * @see soc.common.client.visuals.board.PortPossibilitiesVisual#updateVisible()
     */
    @Override
    protected void updateVisible()
    {
        group.setVisible(visible);
    }
}
