package soc.common.views.behaviour.gameBoard;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.standard.RollDice;
import soc.common.views.widgetsInterface.visuals.GameBoardVisual;
import soc.common.views.widgetsInterface.visuals.HexVisual;
import soc.common.views.widgetsInterface.visuals.PieceVisual;

public class RollDiceBehaviour implements GameBoardBehaviour
{
    private RollDice rollDice;

    public RollDiceBehaviour(RollDice rollDice)
    {
        super();
        this.rollDice = rollDice;
    }

    @Override
    public void setNeutral(GameBoardVisual visual)
    {
        // Sets all hexes back to not darkened and enabled
        for (HexVisual hexVisual : visual.getHexVisuals().values())
        {
            hexVisual.setDarkened(false);
            hexVisual.setEnabled(true);
        }
    }

    @Override
    public void start(GameBoardVisual gameVisual)
    {
        // Shows affected hexes lit up, robber hex red (disabled) and
        // unaffected hexes darkened
        for (HexVisual hexVisual : gameVisual.getHexVisuals().values())
        {
            if (rollDice.getHexesAffected().contains(
                    hexVisual.getHex().getLocation()))
            {
                // If the hex is affected by the dice roll, light it up
                hexVisual.setDarkened(false);
            }
            else
            {
                if (hexVisual.getHex().getLocation().equals(
                        gameVisual.getGame().getRobber().getLocation()))
                {
                    // Robber location, make it look disabled
                    hexVisual.setDarkened(false);
                }
                else
                {
                    // No robber, not affected: set it dark and enabled
                    hexVisual.setDarkened(true);
                }
            }
        }
    }

    @Override
    public void clicked(PieceVisual pieceVisual, GameBoardVisual board)
    {
    }

    @Override
    public void mouseEnter(PieceVisual pieceVisual, GameBoardVisual board)
    {
    }

    @Override
    public void mouseOut(PieceVisual pieceVisual, GameBoardVisual board)
    {
    }

    @Override
    public GameAction getGameAction()
    {
        return rollDice;
    }

}
