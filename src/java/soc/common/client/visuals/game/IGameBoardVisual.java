package soc.common.client.visuals.game;

import soc.common.client.behaviour.game.IGameBehaviour;
import soc.common.client.visuals.board.IBoardVisual;

public interface IGameBoardVisual extends IBoardVisual
{
    public IBoardVisual getBoardVisual();
    public IHexPointsVisual getHexPointsVisual();
    public IHexSidesVisual getHexSidesVisual();
    public void setBehaviour(IGameBehaviour gameBehaviour);
}
