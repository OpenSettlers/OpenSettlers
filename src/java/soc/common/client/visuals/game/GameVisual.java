package soc.common.client.visuals.game;

import soc.common.client.visuals.board.IBoardVisual;
import soc.common.game.IGame;

public class GameVisual implements IGameVisual 
{
    protected IGame game;
    protected IBoardVisual boardVisual;
    protected IHexPointsVisual possiblePointsVisual;
    protected IHexSidesVisual possibleSidesVisual;
    
    @Override
    public IBoardVisual getBoardVisual()
    {
        return boardVisual; 
    }

    @Override
    public IHexPointsVisual getHexPointsVisual()
    {
        return possiblePointsVisual;
    }

    @Override
    public IHexSidesVisual getHexSidesVisual()
    {
        return possibleSidesVisual;
    }

}
