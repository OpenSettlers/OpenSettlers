package soc.gwtClient.game.widgets;

import soc.common.board.Board;
import soc.common.client.behaviour.game.IGameBehaviour;
import soc.common.client.visuals.board.IBoardVisual;
import soc.common.client.visuals.game.IGameBoardVisual;
import soc.common.client.visuals.game.IHexPointsVisual;
import soc.common.client.visuals.game.IHexSidesVisual;
import soc.gwtClient.visuals.svg.SvgBoardVisual;

public class SvgGameBoardVisual extends SvgBoardVisual implements IGameBoardVisual
{

    public SvgGameBoardVisual(int widthInPixels, int heightInPixels, Board b)
    {
        super(widthInPixels, heightInPixels, b);
    }

    @Override
    public IBoardVisual getBoardVisual()
    {
        return this;
    }

    @Override
    public IHexPointsVisual getHexPointsVisual()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IHexSidesVisual getHexSidesVisual()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setBehaviour(IGameBehaviour gameBehaviour)
    {
        // TODO Auto-generated method stub
        
    }

}
