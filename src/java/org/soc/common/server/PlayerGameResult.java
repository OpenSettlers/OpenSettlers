package org.soc.common.server;

public interface PlayerGameResult
{
    public int getRatingDifference();

    public boolean hasGainedRating();

    public boolean hasLostRating();

    public boolean hasWonGame();

    public boolean hasLostGame();

    public PlayerInfo getPlayer();

    public int getVictoryPoints();

}
