package soc.common.server;

public interface PlayerInfo
{
    public int getGamesPlayed();

    public int getGamesWon();

    public int getGamesLost();

    public double getGamesWonPercentage();

    public String getName();

    public String getPasswordHash();

    public String getFavoritePrimaryColor();

    public String getFavoriteSecondaryColor();
}
