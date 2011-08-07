package org.soc.common.server.rating;

import java.util.Date;
import java.util.List;

import org.soc.common.server.PlayerGameResult;
import org.soc.common.server.entities.User;


public interface PlayedGame
{
    public User getWinner();

    public Date getStartedDateTime();

    public Date getEndedDateTime();

    public boolean isLadder();

    public boolean withBots();

    public List<PlayerGameResult> getPlayerGameResults();

    public String getBoardID();

    public boolean hasFinished();

    public int getGameID();

    public void updateRatings();
}