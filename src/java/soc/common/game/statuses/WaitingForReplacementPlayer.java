package soc.common.game.statuses;

import java.util.ArrayList;
import java.util.List;

import soc.common.game.GamePlayer;

/*
 * Status where the game is waiting for a replacement player because a player left the game 
 */
public class WaitingForReplacementPlayer implements GameStatus
{
    private int amountPlayers = 1;
    private List<GamePlayer> gonePlayers = new ArrayList<GamePlayer>();

    @Override
    public boolean isGameBlocking()
    {
        return true;
    }

    public List<GamePlayer> getGonePlayers()
    {
        return gonePlayers;
    }

}
