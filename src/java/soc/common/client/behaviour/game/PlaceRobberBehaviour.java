package soc.common.client.behaviour.game;

import soc.common.actions.gameAction.turnActions.standard.PlaceRobber;
import soc.common.board.Board;
import soc.common.board.hexes.Hex;
import soc.common.board.hexes.LandHex;
import soc.common.client.visuals.IPieceVisual;
import soc.common.client.visuals.board.IBoardVisual;
import soc.common.client.visuals.board.IHexVisual;
import soc.common.client.visuals.game.IGameBoardVisual;
import soc.common.game.Game;

public class PlaceRobberBehaviour implements IGameBehaviour
{
    PlaceRobber placeRobber;
    Game game;
    
    public PlaceRobberBehaviour(PlaceRobber placeRobber, Game game)
    {
        super();
        this.placeRobber = placeRobber;
        this.game = game;
    }

    @Override
    public void setNeutral(IGameBoardVisual visual)
    {
        // TODO: implement when able to iterate of collection of IHexVisuals in IBoardVisual
    }

    @Override
    public void start(IGameBoardVisual gameVisual)
    {
        // TODO: implement when able to iterate of collection of IHexVisuals in IBoardVisual
    }

    @Override
    public void clicked(IPieceVisual pieceVisual, IBoardVisual board)
    {
        if (possiblePosition(pieceVisual))
        {
            placeRobber.setNewLocation(((IHexVisual)pieceVisual).getHex().getLocation());
        }
    }

    @Override
    public void mouseEnter(IPieceVisual pieceVisual, IBoardVisual board)
    {
        if (possiblePosition(pieceVisual))
        {
            pieceVisual.setSelected(true);
        }
    }

    @Override
    public void mouseOut(IPieceVisual pieceVisual, IBoardVisual board)
    {
        if (possiblePosition(pieceVisual))
        {
            pieceVisual.setSelected(false);
        }
    }
    
    /*
     * Returns true if given piece visual is a hexvisual showing a landhex,
     * and the robber is not on given hexvisual position
     */
    private boolean possiblePosition(IPieceVisual pieceVisual)
    {
        if (pieceVisual instanceof IHexVisual)
        {
            IHexVisual hexVisual = (IHexVisual) pieceVisual;
            
            // We need a landhex for a robber, and not the current position of the robber
            if (hexVisual.getHex() instanceof LandHex &&
                !hexVisual.getHex().equals(game.getRobber()))
            {
                return true;
            }
        }
        return false;
    }

}
