package soc.gwtClient.visuals.svg;

import org.vaadin.gwtgraphics.client.Group;
import org.vaadin.gwtgraphics.client.Image;
import org.vaadin.gwtgraphics.client.shape.Path;

import soc.common.board.hexes.Hex;
import soc.gwtClient.game.Point2D;
import soc.gwtClient.images.Resources;
import soc.gwtClient.visuals.abstractVisuals.AbstractHexVisual;
import soc.gwtClient.visuals.abstractVisuals.BoardVisual;
import soc.gwtClient.visuals.abstractVisuals.PieceVisual;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;

public class HexSvg extends AbstractHexVisual implements MouseMoveHandler,
        ClickHandler, MouseOutHandler
{
    private Group group = new Group();
    private Point2D point;
    private Path path;
    private Image hexImage;
    private Path disabledOverlay;
    private Path darkenedOverlay;

    public HexSvg(Hex hex, BoardVisual parent)
    {
        super(hex, parent);

        this.point = getSvgParent().calculatePosition(hex.getLocation());

        path = createMainPath();
        disabledOverlay = createDisabledOverlay();
        darkenedOverlay = createDarkenedOverlay();

        // create the visuals
        this.chit = new ChitSvg(null, parent, getMiddlePoint(point));
        this.territory = new TerritorySvg(parent, null, point);
        this.portPossibilities = new PortPossiblitiesSvg(hex.getLocation(),
                (BoardSvg) parent);
        this.port = new PortSvg(null, parent, getMiddlePoint(point));

        this.chit.setVisible(false);
        this.portPossibilities.setVisible(false);
        this.port.setVisible(true);

        updateHexVisual();

        // HexImage comes first
        group.add(hexImage);

        // Hexagon path drawing is overlayed on texture
        group.add(path);

        // Add the visuals layer
        group.add(((SvgVisual) chit).getVectorObject());
        group.add(((SvgVisual) territory).getVectorObject());
        group.add(((SvgVisual) portPossibilities).getVectorObject());
        group.add(((SvgVisual) port).getVectorObject());

        // DisabledOverlay is last
        group.add(disabledOverlay);
        group.add(darkenedOverlay);

        group.addMouseMoveHandler(this);
        group.addMouseOutHandler(this);
        group.addClickHandler(this);

        this.setHex(hex);
    }

    /**
     * @return the group
     */
    public Group getGroup()
    {
        return group;
    }

    /**
     * @param selected
     *            the selected to set
     */
    @Override
    public PieceVisual setSelected(boolean selected)
    {
        this.selected = selected;
        if (selected)
        {
            path.setFillOpacity(0.5);
            path.setStrokeWidth(15);
        }
        else
        {
            path.setFillOpacity(0);
            path.setStrokeWidth(5);
        }

        return this;
    }

    private BoardSvg getSvgParent()
    {
        return (BoardSvg) parent;
    }

    private ChitSvg getSvgChit()
    {
        return (ChitSvg) chit;
    }

    private TerritorySvg getSvgTerritory()
    {
        return (TerritorySvg) territory;
    }

    private PortPossiblitiesSvg getSvgPortPossibilitiesVisual()
    {
        return (PortPossiblitiesSvg) portPossibilities;
    }

    private PortSvg getSvgPortVisual()
    {
        return (PortSvg) port;
    }

    private Path createMainPath()
    {
        Path result = createPath();

        result.setStrokeWidth(5);
        result.setFillOpacity(0);

        return result;
    }

    private Path createDisabledOverlay()
    {
        Path result = createPath();

        result.setStrokeWidth(5);
        result.setFillColor("Red");
        result.setFillOpacity(0.5);
        result.setVisible(false);

        return result;
    }

    private Path createDarkenedOverlay()
    {
        Path result = createPath();

        result.setFillColor("Black");
        result.setFillOpacity(0.3);
        result.setVisible(false);

        return result;
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
        return new Point2D((int) point.getX(), (int) (point.getY() + parent
                .getHalfHeight()));
    }

    private Path createPath()
    {
        Path result = new Path(point.getX(), point.getY());

        result.lineRelativelyTo((int) parent.getHalfWidth(), (int) parent
                .getBottomHeight());
        result.lineRelativelyTo(0, (int) parent.getSize());
        result.lineRelativelyTo((int) -parent.getHalfWidth(), (int) parent
                .getBottomHeight());
        result.lineRelativelyTo((int) -parent.getHalfWidth(), (int) -parent
                .getBottomHeight());
        result.lineRelativelyTo(0, (int) -parent.getSize());
        result.lineRelativelyTo((int) parent.getHalfWidth(), (int) -parent
                .getBottomHeight());

        return result;
    }

    @Override
    protected void updateHexVisual()
    {
        String img = "";
        if (hex != null)
        {
            img = Resources.hexImage(hex).getURL();
        }
        String color = hex.getColor();

        if (hexImage == null)
        {
            hexImage = new Image(point.getX() - parent.getSize(), point.getY(),
                    parent.getSize() * 2, parent.getSize() * 2, img);
        }
        else
        {
            hexImage.setHref(img);
        }
        path.setStrokeColor(color);
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

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.client.visuals.board.AbstractHexVisual#updateDarkened()
     */
    @Override
    protected void updateDarkened()
    {
        darkenedOverlay.setVisible(darkened);
    }

    @Override
    public void onMouseMove(MouseMoveEvent event)
    {
        parent.getBoardBehaviour().mouseEnter(this, parent);
    }

    @Override
    public void onClick(ClickEvent event)
    {
        parent.getBoardBehaviour().clicked(this, parent);
    }

    @Override
    public void onMouseOut(MouseOutEvent event)
    {
        parent.getBoardBehaviour().mouseOut(this, parent);
    }

    public void resizeAndPosition()
    {
        this.point = getSvgParent().calculatePosition(hex.getLocation());

        // Resize hex texture
        hexImage.setX(point.getX() - parent.getSize());
        hexImage.setY(point.getY());
        hexImage.setHeight(parent.getSize() * 2);
        hexImage.setWidth(parent.getSize() * 2);

        // update main path and disabled overlay path
        path = createMainPath();
        disabledOverlay = createDisabledOverlay();

        // update children visuals size and position
        this.getSvgChit().resizeAndReposition(point);
        this.getSvgPortVisual().resizeAndReposition(point);
        this.getSvgTerritory().resizeAndReposition(point);
        // this.getSvgPortPossibilitiesVisual().resizeAndReposition(point);
    }

}
