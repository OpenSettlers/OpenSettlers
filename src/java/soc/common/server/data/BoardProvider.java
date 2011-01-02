package soc.common.server.data;

import java.util.List;

import soc.common.board.Board;

/*
 * Interface for a source to provide Board instances
 */
public interface BoardProvider
{
    /*
     * Request a board by specified name. Returns null when no board could be
     * found
     */
    public Board getBoardByName(String name);

    /*
     * Request a board by specified name. Returns null when no board was found
     * with the specified ID
     */
    public Board getBoardByID(String id);

    /*
     * Returns a list of zero to N items consisting of boards created by
     * specified user
     */
    public List<Board> getBoardsFromUser(User user);

    /*
     * Returns a list of all boards present on the server
     */
    public List<Board> getAllBoards();
}