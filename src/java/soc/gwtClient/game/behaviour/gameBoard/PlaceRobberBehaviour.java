package soc.gwtClient.game.behaviour.gameBoard;

import java.util.ArrayList;
import java.util.List;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.turnActions.standard.PlaceRobber;
import soc.common.board.hexes.Hex;
import soc.gwtClient.game.behaviour.gameWidget.MoveRobberGameBehaviour;
import soc.gwtClient.game.widgetsInterface.visuals.GameBoardVisual;
import soc.gwtClient.game.widgetsInterface.visuals.HexVisual;
import soc.gwtClient.game.widgetsInterface.visuals.PieceVisual;

public class PlaceRobberBehaviour implements GameBoardBehaviour
{
    private PlaceRobber placeRobber;
    private List<HexVisual> possibleNewLocations = new ArrayList<HexVisual>();
    private MoveRobberGameBehaviour moveRobberGameBehaviour;

    public PlaceRobberBehaviour(MoveRobberGameBehaviour moveRobberGameBehaviour)
    {
        super();
        this.moveRobberGameBehaviour = moveRobberGameBehaviour;
        this.placeRobber = moveRobberGameBehaviour.getPlaceRobber();
    }

    @Override
    public void setNeutral(GameBoardVisual visual)
    {
        for (HexVisual hv : visual.getHexVisuals().values())
        {
            hv.setEnabled(true);
            hv.setDarkened(false);
        }
    }

    @Override
    public void start(GameBoardVisual gameVisual)
    {
        possibleNewLocations.clear();
        for (Hex hex : gameVisual.getHexVisuals().keySet())
        {
            if (hex.isRobberPlaceable())
            {
                if (hex.getLocation().equals(
                        gameVisual.getGame().getRobber().getLocation()))
                {
                    // Disable the current location of the robber
                    gameVisual.getHexVisuals().get(hex).setEnabled(false);
                }
                else
                {
                    // A possible location to place the robber.
                    possibleNewLocations.add(gameVisual.getHexVisuals()
                            .get(hex));
                }
            }
            else
            {
                // Can't place a robber here, darken location
                gameVisual.getHexVisuals().get(hex).setDarkened(true);
            }
        }
    }

    @Override
    public void clicked(PieceVisual pieceVisual, GameBoardVisual board)
    {
        if (possiblePosition(pieceVisual))
        {
            placeRobber.setNewLocation(((HexVisual) pieceVisual).getHex()
                    .getLocation());
            moveRobberGameBehaviour.pickedRobberSpot(this);
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

            if (possibleNewLocations.contains(hexVisual))
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
