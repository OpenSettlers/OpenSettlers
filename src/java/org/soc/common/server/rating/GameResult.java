package org.soc.common.server.rating;

import java.util.Map;

import org.soc.common.server.entities.User;


public interface GameResult
{
    public User getWinner();

    public Map<User, RatingResult> getRatingResults();
}
