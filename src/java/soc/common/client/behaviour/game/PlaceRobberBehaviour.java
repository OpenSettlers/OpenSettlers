package soc.common.client.behaviour.game;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.turnActions.standard.PlaceRobber;
import soc.common.client.visuals.PieceVisual;
import soc.common.client.visuals.board.HexVisual;
import soc.common.client.visuals.game.GameBoardVisual;
import soc.common.game.Game;

public class PlaceRobberBehaviour implements GameBehaviour
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
    public void setNeutral(GameBoardVisual visual)
    {
        // TODO: implement when able to iterate of collection of IHexVisuals in
        // IBoardVisual
    }

    @Override
    public void start(GameBoardVisual gameVisual)
    {
        // TODO: implement when able to iterate of collection of IHexVisuals in
        // IBoardVisual
    }

    @Override
    public void clicked(PieceVisual pieceVisual, GameBoardVisual board)
    {
        if (possiblePosition(pieceVisual))
        {
            placeRobber.setNewLocation(((HexVisual) pieceVisual).getHex()
                    .getLocation());
        }
    }

    @Override
    public void mouseEnter(PieceVisual pieceVisual, GameBoardVisual board)
    {
        if (possiblePosition(pieceVisual))
        {
            pieceVisual.setSelected(true);
        }
    }

    @Override
    public void mouseOut(PieceVisual pieceVisual, GameBoardVisual board)
    {
        if (possiblePosition(pieceVisual))
        {
            pieceVisual.setSelected(false);
        }
    }

    /*
     * Returns true if given piece visual is a hexvisual showing a landhex, and
     * the robber is not on given hexvisual position
     */
    private boolean possiblePosition(PieceVisual pieceVisual)
    {
        if (pieceVisual instanceof HexVisual)
        {
            HexVisual hexVisual = (HexVisual) pieceVisual;

            // We need a landhex for a robber, and not the current position of
            // the robber
            if (hexVisual.getHex().isRobberPlaceable()
                    && !hexVisual.getHex().equals(game.getRobber()))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public GameAction getGameAction()
    {
        return placeRobber;
    }
}
