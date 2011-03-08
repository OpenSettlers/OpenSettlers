package soc.gwtClient.game.widgetsSvg.visuals;

import org.vaadin.gwtgraphics.client.Group;
import org.vaadin.gwtgraphics.client.VectorObject;
import org.vaadin.gwtgraphics.client.shape.Circle;

import soc.common.board.pieces.MovedEvent;
import soc.common.board.pieces.MovedEventHandler;
import soc.common.board.pieces.Robber;
import soc.common.views.widgetsInterface.generic.Point2D;
import soc.gwtClient.game.widgetsAbstract.visuals.AbstractRobberVisual;

public class RobberSvg extends AbstractRobberVisual implements SvgVisual,
        MovedEventHandler
{
    private Group group = new Group();
    private GameBoardSvg parent;
    private Circle head;
    private Circle body;

    public RobberSvg(GameBoardSvg parent, Robber robber)
    {
        super(robber);
        this.parent = parent;

        int headRadius = (int) (parent.getBoardSvg().getHexagonWidth() / 8);
        int bodyRadius = (int) (parent.getBoardSvg().getHexagonWidth() / 6);

        Point2D point = parent.getBoardSvg().calculatePosition(
                robber.getLocation());

        Point2D headLocation = getHeadLocation(point);
        Point2D bodyLocation = getBodyLocation(point);

        head = new Circle(headLocation.getX(), headLocation.getY(), headRadius);
        head.setFillColor("Black");
        head.setStrokeColor("White");
        body = new Circle(bodyLocation.getX(), bodyLocation.getY(), bodyRadius);
        body.setFillColor("Black");
        body.setStrokeColor("White");
        group = new Group();
        group.add(body);
        group.add(head);
        robber.addMoveEventHandler(this);
    }

    @Override
    public VectorObject getVectorObject()
    {
        return group;
    }

    @Override
    public void onMoved(MovedEvent event)
    {
        Point2D point = parent.getBoardSvg().calculatePosition(
                robber.getLocation());

        Point2D headLocation = getHeadLocation(point);
        Point2D bodyLocation = getBodyLocation(point);

        head.setX(headLocation.getX());
        head.setY(headLocation.getY());
        body.setX(bodyLocation.getX());
        body.setY(bodyLocation.getY());
    }

    private Point2D getHeadLocation(Point2D point)
    {
        int hHeadOffset = (int) (parent.getBoardSvg().getHexagonWidth() / 5);
        int vHeadOffset = (int) (parent.getBoardSvg().getHexagonWidth() / 5);
        return new Point2D(point.getX() + hHeadOffset, point.getY()
                + vHeadOffset);
    }

    private Point2D getBodyLocation(Point2D point)
    {
        int hBodyOffset = (int) (parent.getBoardSvg().getHexagonWidth() / 5);
        int vBodyOffset = (int) (parent.getBoardSvg().getHexagonWidth() / 5) * 2;
        return new Point2D(point.getX() + hBodyOffset, point.getY()
                + vBodyOffset);
    }
}
