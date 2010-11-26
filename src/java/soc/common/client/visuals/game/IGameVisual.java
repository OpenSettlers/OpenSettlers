package soc.common.client.visuals.game;

import soc.common.client.visuals.board.IBoardVisual;

public interface IGameVisual
{
    public IBoardVisual getBoardVisual();
    public IHexPointsVisual getHexPointsVisual();
    public IHexSidesVisual getHexSidesVisual();
}
