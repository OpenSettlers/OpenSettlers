package soc.gwtClient.visuals.svg;

import org.vaadin.gwtgraphics.client.VectorObject;
import org.vaadin.gwtgraphics.client.shape.Path;

import soc.common.board.pieces.Road;
import soc.common.client.visuals.game.AbstractRoadVisual;

public class RoadSvg extends AbstractRoadVisual implements SvgVisual
{
    private Path roadPath;

    public RoadSvg(Road road)
    {
        super(road);
    }

    @Override
    public VectorObject getVectorObject()
    {
        return roadPath;
    }

}
