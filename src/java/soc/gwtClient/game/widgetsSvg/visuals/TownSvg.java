package soc.gwtClient.game.widgetsSvg.visuals;

import org.vaadin.gwtgraphics.client.VectorObject;
import org.vaadin.gwtgraphics.client.shape.Path;

import soc.common.board.pieces.Town;
import soc.gwtClient.game.Point2D;
import soc.gwtClient.game.widgetsAbstract.visuals.AbstractTownVisual;

public class TownSvg extends AbstractTownVisual implements SvgVisual
{
    private Path townPath;
    private GameBoardSvg parent;

    public TownSvg(Town town, GameBoardSvg parent)
    {
        super(town);
        this.parent = parent;

        int width = (int) parent.getSize() / 3;
        int roofHeight = (int) parent.getSize() / 4;

        Point2D loc = parent.getBoardSvg().calculatePosition(town.getPoint());
        townPath = new Path(loc.getX() - (width / 2), loc.getY());

        townPath.lineRelativelyTo(0, width);
        townPath.lineRelativelyTo(width, 0);
        townPath.lineRelativelyTo(0, -width);
        townPath.lineRelativelyTo(-width, 0);

        townPath.lineRelativelyTo(width / 2, -roofHeight);
        townPath.lineRelativelyTo(width / 2, roofHeight);

        townPath.setStrokeWidth(2);
        townPath.setFillColor(town.getPlayer().getColor());
    }

    @Override
    public VectorObject getVectorObject()
    {
        return townPath;
    }

}
