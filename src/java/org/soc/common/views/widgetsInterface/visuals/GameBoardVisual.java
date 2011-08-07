package org.soc.common.views.widgetsInterface.visuals;

import java.util.Map;

import org.soc.common.board.pieces.abstractPieces.Piece;
import org.soc.common.board.routing.GraphPoint;
import org.soc.common.board.routing.GraphSide;
import org.soc.common.board.routing.Route;
import org.soc.common.game.Game;
import org.soc.common.views.behaviour.gameBoard.GameBoardBehaviour;


public interface GameBoardVisual extends BoardVisual
{
    // Returns the game instance
    public Game getGame();

    // Returns current behaviour
    public GameBoardBehaviour getBehaviour();

    // Sets given behaviour as the current one
    public void setBehaviour(GameBoardBehaviour gameBehaviour);

    // Factory method to grab a new VisualFactory
    public VisualFactory createVisualFactory();

    // Access to PieceVisuals by Piece
    public Map<Piece, PieceVisual> getPlayerPieceVisuals();

    // Access to PointVisuals by GraphPoint
    public Map<GraphPoint, PointVisual> getPointVisuals();

    // Access to SideVisuals by GraphSide
    public Map<GraphSide, SideVisual> getSideVisuals();

    public void stopBehaviour();

    // Hides longest route when called with null
    public void showLongestRoad(Route route);
}
