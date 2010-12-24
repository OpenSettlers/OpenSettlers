package soc.common.game.statuses;

import soc.common.actions.Action;

/*
 * Represent the current status of the game. Possible statuses:
 * -Playing
 * -PlayersDisconnected
 * -PlayersLeft
 * -Ended
 * -Lobby
 * -Setting up
 */
public interface IGameStatus
{
    public boolean isGameBlocking();

}