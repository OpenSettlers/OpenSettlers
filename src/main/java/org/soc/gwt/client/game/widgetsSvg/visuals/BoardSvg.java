package org.soc.gwt.client.game.widgetsSvg.visuals;

import org.soc.common.board.HexChangedEvent;
import org.soc.common.board.HexChangedEvent.HexChangedHandler;
import org.soc.common.game.board.Board;
import org.soc.common.game.board.HexLocation;
import org.soc.common.game.board.HexPoint;
import org.soc.common.game.board.HexSide;
import org.soc.common.game.board.HexPoint.HexPointType;
import org.soc.common.game.hexes.Hex;
import org.soc.common.views.widgetsInterface.generic.Point2D;
import org.soc.gwt.client.editor.BehaviourChanged;
import org.soc.gwt.client.game.widgetsAbstract.visuals.AbstractBoardVisual;
import org.vaadin.gwtgraphics.client.DrawingArea;
import org.vaadin.gwtgraphics.client.shape.Rectangle;

import com.google.gwt.user.client.ui.Widget;

public class BoardSvg extends AbstractBoardVisual implements HexChangedHandler {
  private DrawingArea drawingArea;
  private Rectangle enabledOverlay;

  public DrawingArea getDrawingArea() {
    return drawingArea;
  }
  public BoardSvg(int widthInPixels, int heightInPixels, Board b) {
    drawingArea = new DrawingArea(widthInPixels, heightInPixels);
    board = b;
    initializeBoard();
    board.hexes().addHexChangedHandler(this);
  }
  private void initializeBoard() {
    // Iterate over all hexes, create a HexVisual and attach event handlers
    for (Hex hex : board.hexes()) {
      final HexSvg hv = new HexSvg(hex, this);
      drawingArea.add(hv.getGroup());
      hexVisuals.put(hex, hv);
    }
    enabledOverlay = new Rectangle(0, 0, drawingArea.getWidth(),
            drawingArea.getHeight());
    enabledOverlay.setFillColor("Black");
    enabledOverlay.setFillOpacity(0.5);
  }
  public Point2D calculatePosition(HexLocation location) {
    double margin = 5;
    double marginLeft = getHalfWidth();
    double x = location.w() * (getHexagonWidth() + margin);
    double y = location.h() * (getPartialHeight() + margin);
    x += marginLeft;
    // Alternate half the width of an hex
    if (location.h() % 2 == 0)
      x += getHalfWidth();
    // center the position (not necessary in 2D view)
    // x -= Hex.getHalfWidth() * board.getWidth();
    // y -= ((Hex.getPartialHeight() * board.getHeight()) +
    // Hex.getBottomHeight()) / 2;
    return new Point2D((int) x, (int) y);
  }
  public Point2D calculatePosition(HexSide location) {
    Point2D result = calculatePosition(location.getHighestOrLeftestHex());
    double x = result.getX();
    double y = result.getY();
    x -= getHalfWidth();
    switch (location.getDirection()) {
      case SLOPEDOWN:
        x += getHexagonWidth() * 0.25;
        y += (getBottomHeight() * 0.5) + getPartialHeight();
        break;
      case SLOPEUP:
        x += getHexagonWidth() * 0.75;
        y += (getBottomHeight() * 0.5) + getPartialHeight();
        break;
      case UPDOWN:
        x += getHexagonWidth();
        y += getHeight() * 0.5;
        break;
    }
    return new Point2D((int) x, (int) y);
  }
  public Point2D calculatePosition(HexPoint location) {
    // get the x,y coordinate of the topmost HexLocation
    Point2D point = calculatePosition(location.getTopMost());
    // Point is immutable, so cache the values
    double x = point.getX();
    double y = point.getY();
    if (location.pointType() == HexPointType.UpperRow1) {
      // x += getHalfWidth();
      y += getHeight();
    } else {
      x += getHalfWidth();
      y += getPartialHeight();
    }
    return new Point2D((int) x, (int) y);
  }
  @Override public void onBehaviourChanged(BehaviourChanged behaviourChanged) {
    editBehaviour = behaviourChanged.getBehaviour();
  }
  @Override protected void updateEnabled() {
    enabledOverlay.setVisible(enabled);
  }
  @Override public Widget asWidget() {
    return drawingArea;
  }
  @Override public void resize(int width, int height) {
    // Ensure underlying drawing area uses up all available space
    drawingArea.setSize(width + "px", height + "px");
    // First, calculate the projected width & height from the old values
    double projectedWidth = (board.getWidth() + 1) * getHexagonWidth();
    double projectedHeight = (board.getHeight() + 1) * getBottomHeight();
    // Then, calculate for width and height a scale factor
    double widthFactor = projectedWidth / width;
    double heightFactor = projectedHeight / height;
    // Determine the factor to use, which is th lowest of the scale factors
    double factor = widthFactor > heightFactor ? heightFactor : widthFactor;
    // Scale the sidelength to the new factor
    sideLength *= factor;
    // Update the rest of the hex sizes
    calculateHexSizes();
    // Let all children resize and reposition themselves
    for (HexVisual hexVisual : hexVisuals.values()) {
      HexSvg svgHexVisual = (HexSvg) hexVisual;
      svgHexVisual.resizeAndPosition();
    }
  }
  @Override public void onHexChanged(HexChangedEvent event) {
    // Grab corresponding hex visual
    HexVisual hv = hexVisuals.get(event.getOldHex());
    // update the hex visual
    hv.setHex(event.getNewHex());
    // Add new hex/hexvisual combo and remove old one
    hexVisuals.put(event.getNewHex(), hv);
    hexVisuals.remove(event.getOldHex());
  }
}
