package soc.common.actions.gameAction;

import soc.common.game.Player;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;

public class StartingPlayerDetermined extends AbstractGameAction
{
    private static final long serialVersionUID = 4916570503194938187L;
    private int diceRoll;
    private Player gameStarter;

    /**
     * @return the gameStarter
     */
    public Player getGameStarter()
    {
        return gameStarter;
    }

    /**
     * @param gameStarter the gameStarter to set
     */
    public StartingPlayerDetermined setGameStarter(Player gameStarter)
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
     * @param diceRoll the diceRoll to set
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


}
