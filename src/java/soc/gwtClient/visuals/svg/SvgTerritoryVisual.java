package soc.gwtClient.visuals.svg;

import org.vaadin.gwtgraphics.client.Image;

import soc.common.board.Territory;
import soc.common.client.visuals.board.TerritoryVisual;
import soc.gwtClient.game.Point2D;

public class SvgTerritoryVisual extends TerritoryVisual
{
    Image image;
    Point2D point;
    /**
     * @return the image
     */
    public Image getImage()
    {
        return image;
    }

    public SvgTerritoryVisual(Territory territory, Point2D point)
    {
        super(territory);

        this.point = point;
        
        image = new Image(point.getX(), point.getY() + 50, 32,32, "");
        
        updateTerritory();
    }
    
    /* (non-Javadoc)
     * @see soc.common.client.visuals.board.TerritoryVisual#updateTerritory()
     */
    @Override
    protected void updateTerritory()
    {
        String img = "";

        if (territory != null)
        {
            if (territory.isMainland())
            {
                img = "images/mainland.png";
            }
            else
            {
                img  = "images/island" + territory.getID() + ".png";
            }
        }
        image.setHref(img);
    }

    /* (non-Javadoc)
     * @see soc.common.client.visuals.board.TerritoryVisual#updateVisible()
     */
    @Override
    protected void updateVisible()
    {
        image.setVisible(visible);
    }
}
