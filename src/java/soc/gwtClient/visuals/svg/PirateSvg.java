package soc.gwtClient.visuals.svg;

import org.vaadin.gwtgraphics.client.Group;
import org.vaadin.gwtgraphics.client.VectorObject;

import soc.common.board.pieces.Pirate;
import soc.common.client.visuals.game.AbstractPirateVisual;

public class PirateSvg extends AbstractPirateVisual implements SvgVisual
{
    Group group = new Group();

    public PirateSvg(Pirate pirate)
    {
        super(pirate);

    }

    @Override
    public VectorObject getVectorObject()
    {
        return group;
    }

}
