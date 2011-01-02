package soc.gwtClient.visuals.svg;

import org.vaadin.gwtgraphics.client.Group;
import org.vaadin.gwtgraphics.client.VectorObject;
import org.vaadin.gwtgraphics.client.shape.Circle;

import soc.common.board.pieces.Robber;
import soc.common.client.visuals.game.AbstractRobberVisual;

public class RobberSvg extends AbstractRobberVisual implements SvgVisual
{
    private Group group = new Group();

    public RobberSvg(Robber robber)
    {
        super(robber);

        Circle head = new Circle(20, 20, 5);
        Circle body = new Circle(20, 30, 10);

        group.add(head);
        group.add(body);

    }

    @Override
    public VectorObject getVectorObject()
    {
        return group;
    }
}
