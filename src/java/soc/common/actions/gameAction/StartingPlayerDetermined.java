package soc.common.actions.gameAction;

import soc.common.game.GamePlayer;
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

        // Enables fluent interface usage
        // http://en.wikipedia.org/wiki/Fluent_interface
        return this;
    }

    @Override
    public boolean isAllowed(TurnPhase turnPhase)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isAllowed(GamePhase gamePhase)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String getToDoMessage()
    {
        return I18n.get().actions().noToDo();
    }

}
