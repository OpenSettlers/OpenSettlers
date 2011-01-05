package soc.common.actions.gameAction.turnActions;

import soc.common.actions.gameAction.AbstractGameAction;
import soc.common.game.Game;
import soc.common.game.gamePhase.DetermineFirstPlayerGamePhase;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;

public class RolledSame extends AbstractGameAction
{
    private static final long serialVersionUID = 1784758212459861730L;
    private int highRoll;

    /**
     * @return the highRoll
     */
    public int getHighRoll()
    {
        return highRoll;
    }

    /**
     * @param highRoll
     *            the highRoll to set
     */
    public RolledSame setHighRoll(int highRoll)
    {
        this.highRoll = highRoll;

        // Enables fluent interface usage
        // http://en.wikipedia.org/wiki/Fluent_interface
        return this;
    }

    @Override
    public boolean isAllowed(TurnPhase turnPhase)
    {
        return false;
    }

    @Override
    public boolean isAllowed(GamePhase gamePhase)
    {
        return gamePhase instanceof DetermineFirstPlayerGamePhase;
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
        game.advanceTurn();

        // TODO: fix message
        message = "Rolled same: " + highRoll;

        super.perform(game);
    }

    @Override
    public String getToDoMessage()
    {
        // TODO: fix message
        return "Highrollers should roll again, highroll:" + highRoll;
    }
}
