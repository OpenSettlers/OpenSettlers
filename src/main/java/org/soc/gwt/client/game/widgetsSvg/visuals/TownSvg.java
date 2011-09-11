package org.soc.gwt.client.game.widgetsSvg.visuals;

import org.soc.common.game.pieces.Town;
import org.soc.common.views.widgetsInterface.generic.Point2D;
import org.soc.gwt.client.game.widgetsAbstract.visuals.AbstractTownVisual;
import org.vaadin.gwtgraphics.client.VectorObject;
import org.vaadin.gwtgraphics.client.shape.Path;


public class TownSvg extends AbstractTownVisual implements SvgVisual
{
    private Path townPath;
    private GameBoardSvg parent;

    public TownSvg(GameBoardSvg parent, Town town)
    {
        super(town);
        this.parent = parent;

        int width = (int) parent.getSize() / 3;
        int roofHeight = (int) parent.getSize() / 4;

        Point2D loc = parent.getBoardSvg().calculatePosition(town.point());
        townPath = new Path(loc.getX() - (width / 2), loc.getY());

        townPath.lineRelativelyTo(0, width);
        townPath.lineRelativelyTo(width, 0);
        townPath.lineRelativelyTo(0, -width);
        townPath.lineRelativelyTo(-width, 0);

        townPath.lineRelativelyTo(width / 2, -roofHeight);
        townPath.lineRelativelyTo(width / 2, roofHeight);

        townPath.setStrokeWidth(2);
        townPath.setFillColor(town.player().color());
    }

    @Override
    public VectorObject getVectorObject()
    {
        return townPath;
    }

}
