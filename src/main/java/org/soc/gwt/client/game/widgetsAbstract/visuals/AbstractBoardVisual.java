package org.soc.gwt.client.game.widgetsAbstract.visuals;

import java.util.HashMap;

import org.soc.common.game.actions.ActionOnBoard;
import org.soc.common.game.board.Board;
import org.soc.common.game.hexes.Hex;
import org.soc.common.views.widgetsInterface.visuals.BoardVisual;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual;
import org.soc.gwt.client.editor.BehaviourChanged;
import org.soc.gwt.client.editor.BehaviourChangedHandler;

public abstract class AbstractBoardVisual extends AbstractPieceVisual implements
        BoardVisual, BehaviourChangedHandler
{
  protected ActionOnBoard editBehaviour = new DefaultBehaviour();
  protected Board board;
  protected HashMap<Hex, HexVisual> hexVisuals = new HashMap<Hex, HexVisual>();
  protected double sideLength = 35;
  private double h;
  private double halfWidth;
  private double height;
  private double width;

  @Override public void clicked(PieceVisual pieceVisual, BoardVisual board)
  {
    editBehaviour.clicked(pieceVisual, board);
  }
  @Override public void mouseEnter(PieceVisual pieceVisual, BoardVisual board)
  {
    editBehaviour.mouseEnter(pieceVisual, board);
  }
  @Override public void mouseOut(PieceVisual pieceVisual, BoardVisual board)
  {
    editBehaviour.mouseOut(pieceVisual, board);
  }
  public HashMap<Hex, HexVisual> hexVisuals()
  {
    return hexVisuals;
  }
  public AbstractBoardVisual()
  {
    calculateHexSizes();
  }
  @Override public Board board()
  {
    return board;
  }
  @Override public ActionOnBoard getBoardBehaviour()
  {
    return editBehaviour;
  }
  @Override public BoardVisual setBoardBehaviour(ActionOnBoard behaviour)
  {
    editBehaviour = behaviour;
    updateBehaviour();
    return this;
  }
  private void updateBehaviour()
  {}
  @Override public void onBehaviourChanged(BehaviourChanged behaviourChanged)
  {
    setBoardBehaviour(behaviourChanged.getBehaviour());
  }
  @Override public void hideTerritories()
  {
    for (HexVisual hexVisual : hexVisuals.values())
      hexVisual.getTerritory().setVisible(false);
  }
  @Override public void showTerritories()
  {
    for (HexVisual hexVisual : hexVisuals.values())
      hexVisual.getTerritory().setVisible(true);
  }
  @Override public double getBottomHeight()
  {
    return h;
  }
  @Override public double getHalfHeight()
  {
    return height / 2;
  }
  @Override public double getHalfWidth()
  {
    return halfWidth;
  }
  @Override public double getHeight()
  {
    return height;
  }
  @Override public double getPartialHeight()
  {
    return sideLength + h;
  }
  @Override public int getSize()
  {
    return (int) sideLength;
  }
  @Override public double getHexagonWidth()
  {
    return width;
  }
  protected double degreesToRadians(double degrees)
  {
    return degrees * Math.PI / 180;
  }
  protected void calculateHexSizes()
  {
    // TODO: come up with descriptive name for "h"
    h = Math.sin(degreesToRadians(30)) * sideLength;
    halfWidth = Math.cos(degreesToRadians(30)) * sideLength;
    height = sideLength + (2 * h);
    width = 2 * halfWidth;
  }
}
