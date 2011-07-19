package org.soc.common.game.statuses;

public class Playing implements GameStatus
{
    private static final long serialVersionUID = -5863963795809197191L;

    @Override
    public boolean isGameBlocking()
    {
        return false;
    }

    @Override
    public String getDescription()
    {
        // TODO fix message
        return "Playing the game";
    }

    @Override
    public boolean isWaitingForPlayers()
    {
        return false;
    }

    @Override
    public boolean isPlaying()
    {
        return true;
    }

}
