package soc.common.server.data;

import java.util.List;

import soc.common.board.Board;
import soc.common.game.User;

/*
 * Interface for a source to provide Board instances
 */
public interface IBoardProvider
{
    public Board getBoardByName(String name);

    public Board getBoardByID(String id);

    public List<Board> getBoardsFromUser(User user);

    public List<Board> getAllBoards();
}