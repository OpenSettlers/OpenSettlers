package soc.gwtClient.lobby;

public interface PlayerGameResult
{
    public int getRatingDifference();

    public boolean hasGainedRating();

    public boolean hasLostRating();

    public boolean hasWonGame();

    public boolean hasLostGame();

    public int getVictoryPoints();

}
