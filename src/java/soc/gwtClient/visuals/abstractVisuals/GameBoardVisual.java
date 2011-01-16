package soc.gwtClient.visuals.abstractVisuals;

import java.util.Map;

import soc.common.board.pieces.PlayerPiece;
import soc.common.board.routing.GraphPoint;
import soc.common.board.routing.GraphSide;
import soc.common.game.Game;
import soc.gwtClient.visuals.behaviour.gameBoard.GameBoardBehaviour;

public interface GameBoardVisual extends BoardVisual
{
    public Game getGame();

    public GameBoardBehaviour getBehaviour();

    public void setBehaviour(GameBoardBehaviour gameBehaviour);

    public VisualFactory createVisualFactory();

    public Map<PlayerPiece, PieceVisual> getPlayerPieceVisuals();

    public Map<GraphPoint, PointVisual> getPointVisuals();

    public Map<GraphSide, SideVisual> getSideVisuals();

    public void stopBehaviour();
}
