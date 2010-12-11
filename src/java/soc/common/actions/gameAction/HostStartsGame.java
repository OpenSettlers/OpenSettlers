package soc.common.actions.gameAction;

import soc.common.game.Game;

public class HostStartsGame extends GameAction
{
    private Game game;

    /**
     * @return the game
     */
    public Game getGame()
    {
        return game;
    }

    /**
     * @param game the game to set
     */
    public HostStartsGame setGame(Game game)
    {
        this.game = game.copy();
    
        // Enables fluent interface usage
        // http://en.wikipedia.org/wiki/Fluent_interface
        return this;
    }

}
