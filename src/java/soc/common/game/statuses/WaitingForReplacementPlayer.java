package soc.common.game.statuses;

import java.util.ArrayList;
import java.util.List;

import soc.common.game.player.GamePlayer;

/*
 * Status where the game is waiting for a replacement player because a player left the game
 */
public class WaitingForReplacementPlayer implements GameStatus
{
    private static final long serialVersionUID = 574256018106230045L;
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

    @Override
    public String getDescription()
    {
        // TODO fix message
        return "Waiting for another player to replace ...";
    }

    @Override
    public boolean isWaitingForPlayers()
    {
        return false;
    }

    @Override
    public boolean isPlaying()
    {
        return false;
    }
}