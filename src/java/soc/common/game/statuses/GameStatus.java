package soc.common.game.statuses;

/*
 * Represent the current status of the game. Possible statuses:
 * -Playing
 * -PlayersDisconnected
 * -PlayersLeft
 * -Ended
 * -Lobby
 * -Setting up
 */
public interface GameStatus
{
    public boolean isGameBlocking();

    public String getDescription();
}