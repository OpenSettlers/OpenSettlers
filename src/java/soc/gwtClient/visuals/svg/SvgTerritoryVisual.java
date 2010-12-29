package soc.gwtClient.visuals.svg;

import org.vaadin.gwtgraphics.client.Image;

import soc.common.board.territories.Territory;
import soc.common.client.visuals.board.IBoardVisual;
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

    public SvgTerritoryVisual(IBoardVisual boardVisual, Territory territory,
            Point2D point)
    {
        super(boardVisual, territory);

        this.point = point;
        int width = parent.getSize() / 2;
        int height = parent.getSize() / 2;

        image = new Image(point.getX(), point.getY() + parent.getSize(), width,
                height, "");

        updateTerritory();
    }

    /*
     * (non-Javadoc)
     * 
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
                img = "images/island" + territory.getID() + ".png";
            }
        }
        image.setHref(img);
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.client.visuals.board.TerritoryVisual#updateVisible()
     */
    @Override
    protected void updateVisible()
    {
        image.setVisible(visible);
    }

    public void resizeAndReposition(Point2D newPoint)
    {
        int width = parent.getSize() / 2;
        int height = parent.getSize() / 2;
        image.setX(newPoint.getX());
        image.setY(newPoint.getY() + parent.getSize());
        image.setHeight(height);
        image.setWidth(width);
    }
}
