package soc.gwtClient.visuals.svg;

import org.vaadin.gwtgraphics.client.Group;
import org.vaadin.gwtgraphics.client.Image;
import org.vaadin.gwtgraphics.client.shape.Path;

import soc.common.board.hexes.AbstractHex;
import soc.common.board.hexes.Hex;
import soc.common.client.visuals.IPieceVisual;
import soc.common.client.visuals.board.HexVisual;
import soc.common.client.visuals.board.IBoardVisual;
import soc.gwtClient.game.Point2D;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;

public class SvgHexVisual extends HexVisual implements MouseMoveHandler,
        ClickHandler, MouseOutHandler
{
    /**
     * @return the group
     */
    public Group getGroup()
    {
        return group;
    }

    private Group group = new Group();
    private Point2D point;
    private final Path p;
    private Image hexImage;
    private Path disabledOverlay;

    /**
     * @param selected
     *            the selected to set
     */
    @Override
    public IPieceVisual setSelected(boolean selected)
    {
        this.selected = selected;
        if (selected)
        {
            p.setFillOpacity(0.5);
            p.setStrokeWidth(15);
        }
        else
        {
            p.setFillOpacity(0);
            p.setStrokeWidth(5);
        }

        // Enables fluent interface usage
        // http://en.wikipedia.org/wiki/Fluent_interface
        return this;
    }

    public SvgHexVisual(Hex hex, IBoardVisual parent, Point2D point)
    {
        super(hex, parent);

        this.point = point;

        p = createPath();
        p.setStrokeWidth(5);
        p.setFillOpacity(0);

        disabledOverlay = createPath();
        disabledOverlay.setStrokeWidth(5);
        disabledOverlay.setFillColor("Black");
        disabledOverlay.setFillOpacity(0.5);
        disabledOverlay.setVisible(false);

        // create the visuals
        this.chit = new SvgChitVisual(null, parent, point);
        this.territory = new SvgTerritoryVisual(null, point);
        this.portPossibilities = new SvgPortPossiblitiesVisual(hex
                .getLocation(), (SvgBoardVisual) parent);
        this.port = new SvgPortVisual(null, parent, getMiddlePoint(point));

        this.chit.setVisible(false);
        this.portPossibilities.setVisible(false);
        this.port.setVisible(true);

        updateHexVisual();

        // HexImage comes first
        group.add(hexImage);

        // Hexagon path drawing is overlayed on texture
        group.add(p);

        // Add the visuals layer
        group.add(((SvgChitVisual) chit).getTerritoryImage());
        group.add(((SvgTerritoryVisual) territory).getImage());
        group.add(((SvgPortPossiblitiesVisual) portPossibilities).getGroup());
        group.add(((SvgPortVisual) port).getGroup());

        // DisabledOverlay is last
        group.add(disabledOverlay);

        group.addMouseMoveHandler(this);
        group.addMouseOutHandler(this);
        group.addClickHandler(this);

        this.setHex(hex);
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.client.visuals.board.HexVisual#updateEnabled()
     */
    @Override
    protected void updateEnabled()
    {
        disabledOverlay.setVisible(!enabled);
    }

    private Point2D getMiddlePoint(Point2D point)
    {
        return new Point2D((int) point.getX(),
                (int) (point.getY() + AbstractHex.getHalfHeight()));
    }

    private Path createPath()
    {
        Path result = new Path(point.getX(), point.getY());

        result.lineRelativelyTo((int) AbstractHex.getHalfWidth(),
                (int) AbstractHex.getBottomHeight());
        result.lineRelativelyTo(0, (int) AbstractHex.getSize());
        result.lineRelativelyTo((int) -AbstractHex.getHalfWidth(),
                (int) AbstractHex.getBottomHeight());
        result.lineRelativelyTo((int) -AbstractHex.getHalfWidth(),
                (int) -AbstractHex.getBottomHeight());
        result.lineRelativelyTo(0, (int) -AbstractHex.getSize());
        result.lineRelativelyTo((int) AbstractHex.getHalfWidth(),
                (int) -AbstractHex.getBottomHeight());

        return result;
    }

    @Override
    protected void updateHexVisual()
    {
        String img = "images/" + hex.getName() + ".png";
        String color = hex.getColor();

        if (hexImage == null)
        {
            hexImage = new Image(point.getX() - 48, point.getY(), 95, 98, img);
        }
        else
        {
            hexImage.setHref(img);
        }
        p.setStrokeColor(color);
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.client.visuals.board.HexVisual#updateVisible()
     */
    @Override
    protected void updateVisible()
    {
        group.setVisible(visible);
    }

    @Override
    public void onMouseMove(MouseMoveEvent event)
    {
        parent.getCurrentBehaviour().mouseEnter(this, parent);
    }

    @Override
    public void onClick(ClickEvent event)
    {
        parent.getCurrentBehaviour().clicked(this, parent);
    }

    @Override
    public void onMouseOut(MouseOutEvent event)
    {
        parent.getCurrentBehaviour().mouseOut(this, parent);
    }

}
