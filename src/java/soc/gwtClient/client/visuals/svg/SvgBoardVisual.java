package soc.gwtClient.client.visuals.svg;

import org.vaadin.gwtgraphics.client.DrawingArea;
import org.vaadin.gwtgraphics.client.animation.Animatable;
import org.vaadin.gwtgraphics.client.animation.Animate;
import org.vaadin.gwtgraphics.client.shape.Rectangle;

import soc.common.board.Board;
import soc.common.board.HexLocation;
import soc.common.board.HexPoint;
import soc.common.board.HexPointType;
import soc.common.board.HexSide;
import soc.common.board.hexes.Hex;
import soc.common.client.visuals.IPieceVisual;
import soc.common.client.visuals.board.BoardVisual;
import soc.gwtClient.client.Point2D;
import soc.gwtClient.editor.BehaviourChanged;

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
        
        board=b;
        
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
        
        enabledOverlay = new Rectangle(0,0,drawingArea.getWidth(), drawingArea.getHeight());
        enabledOverlay.setFillColor("Black");
        enabledOverlay.setFillOpacity(0.5);
    }

    public Point2D calculatePosition(HexLocation location)
    {
        double margin = 5;
        double marginLeft = Hex.getHalfWidth();
        double x = location.getW() * (Hex.getWidth() + margin);
        double y = location.getH() * (Hex.getPartialHeight() + margin);
        
        x+= marginLeft;

        //Alternate half the width of an hex 
        if (location.getH() % 2 == 0) x += Hex.getHalfWidth();

        // center the position (not necessary in 2D view)
        //x -= Hex.getHalfWidth() * board.getWidth();
        //y -= ((Hex.getPartialHeight() * board.getHeight()) + Hex.getBottomHeight()) / 2;

        return new Point2D((int)x, (int)y);
    }
    
    public Point2D CalculatePosition(HexSide location)
    {
        Point2D result = calculatePosition(location.getHighestOrLeftestHex());
        double x = result.getX();
        double y = result.getY();

        switch (location.getDirection())
        {
            case SLOPEDOWN:
                x += Hex.getWidth() * 0.25;
                y += (Hex.getBottomHeight() * 0.5) + Hex.getPartialHeight();
                break;
            case SLOPEUP:
                x += Hex.getWidth() * 0.75;
                y += (Hex.getBottomHeight() * 0.5) + Hex.getPartialHeight();
                break;
            case UPDOWN:
                x += Hex.getWidth();
                y += Hex.getHeight() * 0.5;
                break;
        }
        return new Point2D((int)x, (int)y);
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
            x += Hex.getHalfWidth();
            y += Hex.getHeight();
        }
        else
        {
            x += Hex.getWidth();
            y += Hex.getPartialHeight();
        }

        return point;
    }
    
    @Override
    public void onBehaviourChanged(BehaviourChanged behaviourChanged)
    {
        editBehaviour = behaviourChanged.getBehaviour();
    }

    /* (non-Javadoc)
     * @see soc.common.client.visuals.PieceVisual#updateEnabled()
     */
    @Override
    protected void updateEnabled()
    {
        enabledOverlay.setVisible(enabled);
    }
}
