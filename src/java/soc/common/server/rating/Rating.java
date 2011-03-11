package soc.common.server.rating;

import java.io.Serializable;
import java.util.List;

public interface Rating extends Serializable
{
    public int getRating();

    public List<PlayedGame> getPlayedGames();

    public void addRating(PlayedGame playedGame);

    public int getPlayedGamesAmount();
}
