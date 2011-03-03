package soc.common.server.rating;

import java.util.Date;
import java.util.List;

import soc.common.game.Game;
import soc.common.server.PlayerGameResult;
import soc.common.server.entities.User;

public class PlayedGameImpl implements PlayedGame
{
    private String boardId;
    private Date endedDateTime;
    private int gameId;
    private Date startedDateTime;
    private boolean ladder;
    private boolean withBots;
    private User winner;

    public PlayedGameImpl(Game game)
    {
        this.gameId = game.getId();
        this.startedDateTime = game.getStartedDateTime();
        this.ladder = game.getGameSettings().getIsLadder().isLadder();
    }

    @Override
    public String getBoardID()
    {
        return boardId;
    }

    @Override
    public Date getEndedDateTime()
    {
        return endedDateTime;
    }

    @Override
    public int getGameID()
    {
        return gameId;
    }

    @Override
    public List<PlayerGameResult> getPlayerGameResults()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Date getStartedDateTime()
    {
        return startedDateTime;
    }

    @Override
    public boolean hasFinished()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isLadder()
    {
        return ladder;
    }

    @Override
    public void updateRatings()
    {
    }

    @Override
    public boolean withBots()
    {
        return withBots;
    }

    @Override
    public User getWinner()
    {
        return winner;
    }

}
