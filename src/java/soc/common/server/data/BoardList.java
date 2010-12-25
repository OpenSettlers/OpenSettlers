package soc.common.server.data;

import java.util.ArrayList;
import java.util.List;

import soc.common.board.Board;
import soc.common.game.User;

public class BoardList implements IBoardProvider
{
    private List<Board> boards = new ArrayList<Board>();

    @Override
    public List<Board> getAllBoards()
    {
        return boards;
    }

    @Override
    public Board getBoardByID(String id)
    {
        for (Board board : boards)
        {
            if (board.getBoardSettings().getId().equals(id))
            {
                return board;
            }
        }
        
        return null;
    }

    @Override
    public Board getBoardByName(String name)
    {
        for (Board board : boards)
        {
            if (board.getBoardSettings().getName().equals(name))
            {
                return board;
            }
        }
        
        return null;
    }

    @Override
    public List<Board> getBoardsFromUser(User user)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
