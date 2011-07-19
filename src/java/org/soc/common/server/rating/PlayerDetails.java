package org.soc.common.server.rating;

import java.util.Date;
import java.util.List;

import org.soc.common.board.Board;
import org.soc.common.board.hexes.Hex;
import org.soc.common.board.resources.ResourceList;
import org.soc.common.server.entities.User;


public interface PlayerDetails
{
    /*
     * the player these details are from
     */
    public User getPlayer();

    /*
     * Current ratings per board
     */
    public List<BoardRating> getBoardRatings();

    /*
     * Current ratings per variant
     */
    public List<VariantRating> getVariantRatings();

    /*
     * Current rating for all rated games
     */
    public AllRating getAllRating();

    /*
     * Date when registered
     */
    public Date getRegisteredDate();

    /*
     * The board of the player used for the MetaGame
     */
    public Board getBoard();

    /*
     * Players can build stuff using resources
     */
    public ResourceList getResources();

    /*
     * Players can trade Hexes for resources, or replace a NoneHex for an Hex
     */
    public List<Hex> getHexes();

    /*
     * Adds a rating to these player details
     */
    public void addRating(PlayedGame playedGame);

}
