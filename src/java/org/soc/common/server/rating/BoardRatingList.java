package org.soc.common.server.rating;

import java.util.ArrayList;
import java.util.List;

public class BoardRatingList
{
    private List<BoardRating> ratings = new ArrayList<BoardRating>();

    public void addRating(PlayedGame playedGame)
    {
        BoardRating rating = getByBoardID(playedGame.getBoardID());
        if (rating == null)
            rating = new BoardRatingImpl();
    }

    /*
     * Returns rating for given boardID, or null if no such rating exists
     */
    private BoardRating getByBoardID(String boardID)
    {
        for (BoardRating board : ratings)
            if (board.getBoardUuid().toLowerCase()
                    .equals(boardID.toLowerCase()))
                return board;

        return null;
    }
}
