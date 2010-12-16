package soc.common.actions.gameAction;

import soc.common.game.Player;

public class StartingPlayerDetermined extends GameAction
{
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


}
