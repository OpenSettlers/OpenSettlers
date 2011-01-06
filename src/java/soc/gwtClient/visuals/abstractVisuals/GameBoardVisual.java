package soc.gwtClient.visuals.abstractVisuals;

import java.util.List;
import java.util.Map;

import soc.common.board.HexPoint;
import soc.common.board.HexSide;
import soc.common.board.hexes.Hex;
import soc.common.board.pieces.PlayerPiece;
import soc.common.board.routing.GraphPoint;
import soc.common.board.routing.GraphSide;
import soc.common.game.Game;
import soc.gwtClient.visuals.behaviour.BehaviourDoneEventHandler;
import soc.gwtClient.visuals.behaviour.game.GameBehaviour;

import com.google.gwt.event.shared.HandlerRegistration;

public interface GameBoardVisual extends BoardVisual
{
    public Game getGame();

    public GameBehaviour getBehaviour();

    public void setBehaviour(GameBehaviour gameBehaviour);

    public void setSelectablePoints(List<HexPoint> points);

    public void disablePoints();

    public void setSelectableSides(List<HexSide> sides);

    public void disableSides();

    public void setSelectableHexes(List<Hex> hexes);

    public void disableHexes();

    public VisualFactory createVisualFactory();

    public Map<PlayerPiece, PieceVisual> getPlayerPieceVisuals();

    public Map<GraphPoint, PointVisual> getPointVisuals();

    public Map<GraphSide, SideVisual> getSideVisuals();

    public void onBehaviourDone();

    public HandlerRegistration addBehaviourDoneEventHandler(
            BehaviourDoneEventHandler handler);
}
