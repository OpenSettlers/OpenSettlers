package soc.common.client.behaviour.game;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.turnActions.standard.RollDice;
import soc.common.client.visuals.PieceVisual;
import soc.common.client.visuals.board.HexVisual;
import soc.common.client.visuals.game.GameBoardVisual;

public class RollDiceBehaviour implements GameBehaviour
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
        for (HexVisual hexVisual : visual.getHexVisuals().values())
        {
            hexVisual.setDarkened(false);
            hexVisual.setEnabled(true);
        }
    }

    @Override
    public void start(GameBoardVisual gameVisual)
    {
        for (HexVisual hexVisual : gameVisual.getHexVisuals().values())
        {
            if (rollDice.getHexesAffected().contains(
                    hexVisual.getHex().getLocation()))
            {
                hexVisual.setDarkened(false);
            }
            else
            {
                if (hexVisual.getHex().getLocation().equals(
                        gameVisual.getGame().getRobber().getLocation()))
                {
                    hexVisual.setEnabled(false);
                }
                {
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
