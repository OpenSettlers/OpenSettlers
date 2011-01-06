package soc.gwtClient.visuals.svg;

import org.vaadin.gwtgraphics.client.VectorObject;
import org.vaadin.gwtgraphics.client.shape.Path;

import soc.common.board.pieces.City;
import soc.gwtClient.visuals.abstractVisuals.AbstractCityVisual;

public class CitySvg extends AbstractCityVisual implements SvgVisual
{
    private Path cityPath;

    public CitySvg(City city)
    {
        super(city);

    }

    @Override
    public VectorObject getVectorObject()
    {
        return cityPath;
    }

}
