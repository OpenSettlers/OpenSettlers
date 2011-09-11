package org.soc.common.views.widgetsInterface.visuals;

import java.util.Map;

import org.soc.common.game.Game;
import org.soc.common.game.actions.ActionOnGameBoard;
import org.soc.common.game.board.GraphPoint;
import org.soc.common.game.board.GraphSide;
import org.soc.common.game.board.Route;
import org.soc.common.game.pieces.Piece;

public interface GameBoardVisual extends BoardVisual
{
  // Returns the game instance
  public Game game();
  // Returns current behaviour
  public ActionOnGameBoard getBehaviour();
  // Sets given behaviour as the current one
  public void setBehaviour(ActionOnGameBoard gameBehaviour);
  // Factory method to grab a new VisualFactory
  public VisualFactory createVisualFactory();
  // Access to PieceVisuals by Piece
  public Map<Piece, PieceVisual> getPlayerPieceVisuals();
  // Access to PointVisuals by GraphPoint
  public Map<GraphPoint, PointVisual> pointVisuals();
  // Access to SideVisuals by GraphSide
  public Map<GraphSide, SideVisual> getSideVisuals();
  public void stopBehaviour();
  // Hides longest route when called with null
  public void showLongestRoad(Route route);
}
