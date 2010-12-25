package soc.gwtClient.visuals.svg;

import org.vaadin.gwtgraphics.client.DrawingArea;
import org.vaadin.gwtgraphics.client.shape.Rectangle;

import soc.common.board.Board;
import soc.common.board.HexLocation;
import soc.common.board.HexPoint;
import soc.common.board.HexPointType;
import soc.common.board.HexSide;
import soc.common.board.hexes.AbstractHex;
import soc.common.board.hexes.Hex;
import soc.common.client.visuals.board.BoardVisual;
import soc.gwtClient.editor.BehaviourChanged;
import soc.gwtClient.game.Point2D;

import com.google.gwt.user.client.ui.Widget;

public class SvgBoardVisual extends BoardVisual
{
    private DrawingArea drawingArea;
    private Rectangle enabledOverlay;

    /**
     * @return the drawingArea
     */
    public DrawingArea getDrawingArea()
    {
        return drawingArea;
    }

    public SvgBoardVisual(int widthInPixels, int heightInPixels, Board b)
    {
        drawingArea = new DrawingArea(widthInPixels, heightInPixels);

        board = b;

        initializeBoard();
    }

    private void initializeBoard()
    {
        // Iterate over all hexes, create a HexVisual and attach event handlers
        for (Hex hex : board.getHexes())
        {
            Point2D point = calculatePosition(hex.getLocation());

            final SvgHexVisual hv = new SvgHexVisual(hex, this, point);

            drawingArea.add(hv.getGroup());
            hexVisuals.add(hv);
        }

        enabledOverlay = new Rectangle(0, 0, drawingArea.getWidth(),
                drawingArea.getHeight());
        enabledOverlay.setFillColor("Black");
        enabledOverlay.setFillOpacity(0.5);
    }

    public Point2D calculatePosition(HexLocation location)
    {
        double margin = 5;
        double marginLeft = AbstractHex.getHalfWidth();
        double x = location.getW() * (AbstractHex.getWidth() + margin);
        double y = location.getH() * (AbstractHex.getPartialHeight() + margin);

        x += marginLeft;

        // Alternate half the width of an hex
        if (location.getH() % 2 == 0)
            x += AbstractHex.getHalfWidth();

        // center the position (not necessary in 2D view)
        // x -= Hex.getHalfWidth() * board.getWidth();
        // y -= ((Hex.getPartialHeight() * board.getHeight()) +
        // Hex.getBottomHeight()) / 2;

        return new Point2D((int) x, (int) y);
    }

    public Point2D CalculatePosition(HexSide location)
    {
        Point2D result = calculatePosition(location.getHighestOrLeftestHex());
        double x = result.getX();
        double y = result.getY();

        switch (location.getDirection())
        {
        case SLOPEDOWN:
            x += AbstractHex.getWidth() * 0.25;
            y += (AbstractHex.getBottomHeight() * 0.5)
                    + AbstractHex.getPartialHeight();
            break;
        case SLOPEUP:
            x += AbstractHex.getWidth() * 0.75;
            y += (AbstractHex.getBottomHeight() * 0.5)
                    + AbstractHex.getPartialHeight();
            break;
        case UPDOWN:
            x += AbstractHex.getWidth();
            y += AbstractHex.getHeight() * 0.5;
            break;
        }
        return new Point2D((int) x, (int) y);
    }

    public Point2D CalculatePosition(HexPoint location)
    {
        // get the x,y coordinate of the HexLocation
        Point2D point = calculatePosition(location.getTopMost());

        // Point is immutable, so cache the values
        double x = point.getX();
        double y = point.getY();

        if (location.getPointType() == HexPointType.UPPERROW1)
        {
            x += AbstractHex.getHalfWidth();
            y += AbstractHex.getHeight();
        }
        else
        {
            x += AbstractHex.getWidth();
            y += AbstractHex.getPartialHeight();
        }

        return point;
    }

    @Override
    public void onBehaviourChanged(BehaviourChanged behaviourChanged)
    {
        editBehaviour = behaviourChanged.getBehaviour();
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.client.visuals.PieceVisual#updateEnabled()
     */
    @Override
    protected void updateEnabled()
    {
        enabledOverlay.setVisible(enabled);
    }

    @Override
    public Widget asWidget()
    {
        return drawingArea;
    }
}
