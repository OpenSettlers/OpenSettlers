package org.soc.gwt.client.game.widgetsSvg.visuals;

import org.soc.common.board.territories.Territory;
import org.soc.common.views.widgetsInterface.generic.Point2D;
import org.soc.common.views.widgetsInterface.visuals.BoardVisual;
import org.soc.gwt.client.game.widgetsAbstract.visuals.AbstractTerritoryVisual;
import org.soc.gwt.client.images.Resources;
import org.vaadin.gwtgraphics.client.Image;
import org.vaadin.gwtgraphics.client.VectorObject;


public class TerritorySvg extends AbstractTerritoryVisual implements SvgVisual
{
    Image image;
    Point2D point;

    public TerritorySvg(BoardVisual boardVisual, Territory territory,
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
     * @see org.soc.common.client.visuals.board.TerritoryVisual#updateTerritory()
     */
    @Override
    protected void updateTerritory()
    {
        String img = "";

        if (territory != null)
        {
            if (territory.isMainland())
            {
                img = Resources.icons().mainland32().getURL();
            } else
            {
                img = Resources.island(territory.getID()).getURL();
            }
        }
        image.setHref(img);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.client.visuals.board.TerritoryVisual#updateVisible()
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

    @Override
    public VectorObject getVectorObject()
    {
        return image;
    }
}
