package soc.gwt.client.game.widgetsSvg.visuals;

import org.vaadin.gwtgraphics.client.Group;
import org.vaadin.gwtgraphics.client.VectorObject;

import soc.common.board.pieces.Pirate;
import soc.gwt.client.game.widgetsAbstract.visuals.AbstractPirateVisual;

public class PirateSvg extends AbstractPirateVisual implements SvgVisual
{
    Group group = new Group();
    GameBoardSvg parent;

    public PirateSvg(GameBoardSvg parent, Pirate pirate)
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
