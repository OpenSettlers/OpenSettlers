package soc.common.views.widgetsInterface.visuals;

import java.util.Map;

import soc.common.board.pieces.abstractPieces.Piece;
import soc.common.board.routing.GraphPoint;
import soc.common.board.routing.GraphSide;
import soc.common.board.routing.Route;
import soc.common.game.Game;
import soc.common.views.behaviour.gameBoard.GameBoardBehaviour;

public interface GameBoardVisual extends BoardVisual
{
    public Game getGame();

    public GameBoardBehaviour getBehaviour();

    public void setBehaviour(GameBoardBehaviour gameBehaviour);

    public VisualFactory createVisualFactory();

    public Map<Piece, PieceVisual> getPlayerPieceVisuals();

    public Map<GraphPoint, IPointVisual> getPointVisuals();

    public Map<GraphSide, ISideVisual> getSideVisuals();

    public void stopBehaviour();

    public void showLongestRoad(Route route);
}
