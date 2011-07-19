package org.soc.common.server.rating;

/*
 * Rating for a particular board
 */
public interface BoardRating extends Rating
{
    public String getBoardUuid();
}
