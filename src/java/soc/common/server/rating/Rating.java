package soc.common.server.rating;

import java.util.List;

public interface Rating
{
    public int getRating();

    public List<PlayedGame> getPlayedGames();

    public void addRating(PlayedGame playedGame);

    public int getPlayedGamesAmount();
}
