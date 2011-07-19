package soc.gwt.client.game.widgetsSvg.visuals;

import org.vaadin.gwtgraphics.client.VectorObject;
import org.vaadin.gwtgraphics.client.shape.Path;

import soc.common.board.pieces.City;
import soc.common.views.widgetsInterface.generic.Point2D;
import soc.gwt.client.game.widgetsAbstract.visuals.AbstractCityVisual;

public class CitySvg extends AbstractCityVisual implements SvgVisual
{
    private Path cityPath;
    private GameBoardSvg parent;

    public CitySvg(GameBoardSvg parent, City city)
    {
        super(city);
        this.parent = parent;

        int side = (int) (parent.getHexagonWidth() / 6);

        Point2D point = parent.getBoardSvg().calculatePosition(city.getPoint());

        cityPath = new Path(point.getX() - side, point.getY() + side);

        cityPath.lineRelativelyTo(side * 2, 0);
        cityPath.lineRelativelyTo(0, -side);
        cityPath.lineRelativelyTo(-side, 0);
        cityPath.lineRelativelyTo(0, -(side / 2));
        cityPath.lineRelativelyTo(-(side / 2), -(side / 2));
        cityPath.lineRelativelyTo(-(side / 2), side / 2);
        cityPath.lineRelativelyTo(0, (side * 3) / 2);
        cityPath.setFillColor(city.getPlayer().getColor());
        cityPath.setStrokeWidth(2);
    }

    @Override
    public VectorObject getVectorObject()
    {
        return cityPath;
    }

}
