package soc.gwtClient.visuals.svg;

import org.vaadin.gwtgraphics.client.VectorObject;
import org.vaadin.gwtgraphics.client.shape.Path;

import soc.common.board.pieces.Town;
import soc.common.client.visuals.game.AbstractTownVisual;

public class TownSvg extends AbstractTownVisual implements SvgVisual
{
    private Path townPath;

    public TownSvg(Town town)
    {
        super(town);
    }

    @Override
    public VectorObject getVectorObject()
    {
        return townPath;
    }

}
