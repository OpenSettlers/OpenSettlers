package soc.common.server;

public interface PlayerGameResult
{
    public int getRatingDifference();

    public boolean hasGainedRating();

    public boolean hasLostRating();

    public boolean hasWonGame();

    public boolean hasLostGame();

    public Playerrrrr getPlayer();

    public int getVictoryPoints();

}
