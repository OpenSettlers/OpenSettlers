package soc.common.client.visuals.game;

import java.util.List;

import soc.common.board.HexPoint;
import soc.common.board.HexSide;
import soc.common.board.hexes.Hex;
import soc.common.client.behaviour.game.GameBehaviour;
import soc.common.client.visuals.board.BoardVisual;
import soc.common.game.Game;

public interface GameBoardVisual extends BoardVisual
{
    public Game getGame();

    public void setBehaviour(GameBehaviour gameBehaviour);

    public void setSelectablePoints(List<HexPoint> points);

    public void disablePoints();

    public void setSelectableSides(List<HexSide> sides);

    public void disableSides();

    public void setSelectableHexes(List<Hex> hexes);

    public void disableHexes();

    public VisualFactory createVisualFactory();
}
