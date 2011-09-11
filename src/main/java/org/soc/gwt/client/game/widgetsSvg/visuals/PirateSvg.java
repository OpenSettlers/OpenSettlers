package org.soc.gwt.client.game.widgetsSvg.visuals;

import org.soc.common.game.pieces.Pirate;
import org.soc.gwt.client.game.widgetsAbstract.visuals.AbstractPirateVisual;
import org.vaadin.gwtgraphics.client.Group;
import org.vaadin.gwtgraphics.client.VectorObject;


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
