package soc.common.actions.gameAction;

import soc.common.game.Game;
import soc.common.game.GamePlayer;
import soc.common.game.gamePhase.DetermineFirstPlayerGamePhase;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;
import soc.common.internationalization.I18n;

public class StartingPlayerDetermined extends AbstractGameAction
{
    private static final long serialVersionUID = 4916570503194938187L;
    private int diceRoll;
    private GamePlayer gameStarter;

    /**
     * @return the gameStarter
     */
    public GamePlayer getGameStarter()
    {
        return gameStarter;
    }

    /**
     * @param gameStarter
     *            the gameStarter to set
     */
    public StartingPlayerDetermined setGameStarter(GamePlayer gameStarter)
    {
        this.gameStarter = gameStarter;

        return this;
    }

    /**
     * @return the diceRoll
     */
    public int getDiceRoll()
    {
        return diceRoll;
    }

    /**
     * @param diceRoll
     *            the diceRoll to set
     */
    public StartingPlayerDetermined setDiceRoll(int diceRoll)
    {
        this.diceRoll = diceRoll;
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

    @Override
    public String getToDoMessage()
    {
        return I18n.get().actions().noToDo();
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
        // TODO: It would be nice if players are re-ordered such that the
        // highroller starts, and the player after the highroller comes second,
        // etc.
        game.setGameStarter(gameStarter);
        game.advanceTurn();
        message = "Starting player determined: "
                + game.getGameStarter().getUser().getName();
        super.perform(game);
    }

}
