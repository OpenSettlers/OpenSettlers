package soc.common.client.visuals.game;

import soc.common.board.Board;
import soc.common.client.behaviour.IInteractionBehaviour;
import soc.common.client.behaviour.game.IGameBehaviour;
import soc.common.client.visuals.IPieceVisual;
import soc.common.client.visuals.board.IBoardVisual;
import soc.common.game.Game;

public abstract class GameBoardVisual implements IGameBoardVisual
{
    protected Game game;
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

    @Override
    public void setBehaviour(IGameBehaviour gameBehaviour)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public Board getBoard()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IInteractionBehaviour getCurrentBehaviour()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void hideTerritories()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public IBoardVisual setInteractionBehaviour(IInteractionBehaviour behaviour)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void showTerritories()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isEnabled()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isSelected()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isVisible()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public IPieceVisual setEnabled(boolean enabled)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IPieceVisual setSelected(boolean selected)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IPieceVisual setVisible(boolean visible)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
