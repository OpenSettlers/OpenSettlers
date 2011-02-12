package soc.common.game.statuses;

import java.io.Serializable;

/*
 * Represent the current status of the game. Possible statuses:
 * -Playing
 * -PlayersDisconnected
 * -PlayersLeft
 * -Ended
 * -Lobby
 * -Setting up
 */
public interface GameStatus extends Serializable
{
    /*
     * Whether or not the game can continue while in this state
     */
    public boolean isGameBlocking();

    public String getDescription();
}