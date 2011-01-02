package soc.common.actions.gameAction;

import soc.common.actions.Action;
import soc.common.game.Game;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;
import soc.common.game.statuses.GameStatus;

public interface GameAction extends Action
{
    /*
     * Returns true if player is allowed to play this action in given TurnPhase
     */
    public boolean isAllowed(TurnPhase turnPhase);

    /*
     * Returns true if player is allowed to play this action in given GamePhase
     */
    public boolean isAllowed(GamePhase gamePhase);

    /*
     * Returns true if player is allowed to play this action in given GameStatus
     */
    public boolean isAllowed(GameStatus gameStatus);

    /*
     * Subclasses should call this method after they have performed their
     * specific implementation (at the end of the method)
     */
    public void perform(Game game);

    /*
     * Returns true when this action is allowed Since we're deserializing raw
     * send data, we want to check it
     */
    public boolean isValid(Game game);

    /**
     * @return Message explaining why this action is in invalid state
     */
    public String getInvalidMessage();

    /**
     * @return When an action is required to perform for a user, this message is
     *         displayed
     */
    public String getToDoMessage();
}
