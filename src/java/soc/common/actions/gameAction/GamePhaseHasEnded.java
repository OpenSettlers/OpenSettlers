package soc.common.actions.gameAction;

import soc.common.game.Game;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;
import soc.common.internationalization.I18n;

/*
 * Announces a gamephase which has been ended
 */
public class GamePhaseHasEnded extends AbstractGameAction
{
    private static final long serialVersionUID = 3377193429519428414L;
    private GamePhase endedGamePhase;
    private GamePhase newPhase;

    /**
     * @return the newPhase
     */
    public GamePhase getNewPhase()
    {
        return newPhase;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.actions.gameAction.GameAction#perform(soc.common.game.Game)
     */
    @Override
    public void perform(Game game)
    {
        endedGamePhase = game.getCurrentPhase();

        newPhase = endedGamePhase.next(game);

        // Start the next phase
        newPhase.start(game);

        // Advance the phase to next phase
        game.setCurrentPhase(newPhase);

        // TODO: fix message
        message = endedGamePhase.getName() + " has ended, entering"
                + newPhase.getName();

        super.perform(game);
    }

    /**
     * @return the endedGamePhase
     */
    public GamePhase getEndedGamePhase()
    {
        return endedGamePhase;
    }

    /**
     * @param endedGamePhase
     *            the endedGamePhase to set
     */
    public GamePhaseHasEnded setEndedGamePhase(GamePhase endedGamePhase)
    {
        this.endedGamePhase = endedGamePhase;

        return this;
    }

    @Override
    public boolean isAllowed(TurnPhase turnPhase)
    {
        return true;
    }

    @Override
    public boolean isAllowed(GamePhase gamePhase)
    {
        return true;
    }

    @Override
    public String getToDoMessage()
    {
        return I18n.get().actions().noToDo();
    }
}
