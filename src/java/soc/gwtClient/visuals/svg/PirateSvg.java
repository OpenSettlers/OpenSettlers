package soc.gwtClient.visuals.svg;

import org.vaadin.gwtgraphics.client.Group;
import org.vaadin.gwtgraphics.client.VectorObject;

import soc.common.board.pieces.Pirate;
import soc.gwtClient.visuals.abstractVisuals.AbstractPirateVisual;

public class PirateSvg extends AbstractPirateVisual implements SvgVisual
{
    Group group = new Group();
    GameBoardSvg parent;

    public PirateSvg(Pirate pirate, GameBoardSvg parent)
    {
        super(pirate);
        this.parent = parent;

    }

    @Override
    public VectorObject getVectorObject()
    {
        return group;
    }

}
