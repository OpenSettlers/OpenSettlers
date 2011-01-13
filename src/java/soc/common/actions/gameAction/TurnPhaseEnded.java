package soc.common.actions.gameAction;

import soc.common.game.Game;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.PlayTurnsGamePhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;

public class TurnPhaseEnded extends AbstractGameAction
{
    private static final long serialVersionUID = -7428297105477179042L;

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.actions.gameAction.AbstractGameAction#isValid(soc.common.game
     * .Game)
     */
    @Override
    public boolean isValid(Game game)
    {
        return super.isValid(game);
    }

    @Override
    public String getToDoMessage()
    {
        // TODO fix message
        return "End TurnPhase";
    }

    @Override
    public boolean isAllowed(TurnPhase turnPhase)
    {
        return true;
    }

    @Override
    public boolean isAllowed(GamePhase gamePhase)
    {
        return gamePhase instanceof PlayTurnsGamePhase;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.actions.gameAction.AbstractGameAction#perform(soc.common.game
     * .Game)
     */
    @Override
    public void perform(Game game)
    {
        PlayTurnsGamePhase playTurns = (PlayTurnsGamePhase) game
                .getCurrentPhase();

        TurnPhase oldPhase = playTurns.getTurnPhase();
        game.advanceTurnPhase();
        TurnPhase newPhase = playTurns.getTurnPhase();

        String newPhaseName = "";
        String oldPhaseName = "";
        if (newPhase == null)
        {
            newPhaseName = "ended turn";
            oldPhaseName = "null";
        }
        else
        {
            newPhaseName = newPhase.getName();
        }
        if (oldPhase == null)
        {
            oldPhaseName = "null";
        }
        else
        {
            oldPhaseName = newPhase.getName();
        }
        // TODO: fix message
        try
        {
            message = "Ended " + oldPhaseName + ", started " + newPhaseName;
        }
        catch (Exception e)
        {
            throw new AssertionError();
        }

        super.perform(game);
    }
}
