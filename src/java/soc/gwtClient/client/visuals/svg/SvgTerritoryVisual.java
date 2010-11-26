package soc.gwtClient.client.visuals.svg;

import org.vaadin.gwtgraphics.client.Image;

import soc.common.board.Territory;
import soc.common.client.visuals.board.TerritoryVisual;
import soc.gwtClient.client.Point2D;


public class SvgTerritoryVisual extends TerritoryVisual
{
    Image image;
    /**
     * @return the image
     */
    public Image getImage()
    {
        return image;
    }


    Point2D point;

    public SvgTerritoryVisual(Territory territory, Point2D point)
    {
        super(territory);
        // TODO Auto-generated constructor stub

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
                img  = "/images/island" + territory.getID() + ".png";
            }
        }
        image.setHref(img);
    }
}
