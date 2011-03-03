package soc.common.server.rating;

import java.util.List;

public class BoardRatingImpl implements BoardRating
{
    private String boardId = "";
    private int rating = 1500;
    private int playedGames = 0;

    public BoardRatingImpl(String boardId)
    {
        super();
        this.boardId = boardId;
    }

    public BoardRatingImpl()
    {
    }

    @Override
    public String getBoardUuid()
    {
        return boardId;
    }

    @Override
    public void addRating(PlayedGame playedGame)
    {

    }

    @Override
    public List<PlayedGame> getPlayedGames()
    {
        return null;
    }

    @Override
    public int getRating()
    {
        return rating;
    }

    @Override
    public int getPlayedGamesAmount()
    {
        return playedGames;
    }

}
