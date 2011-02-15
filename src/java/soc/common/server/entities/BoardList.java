package soc.common.server.entities;

import java.util.ArrayList;
import java.util.List;

import soc.common.board.Board;

public class BoardList implements BoardProvider
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
    public List<Board> getBoardsFromUser(User user)
    {
        List<Board> result = new ArrayList<Board>();

        for (Board board : boards)
        {
            if (board.getBoardSettings().getName().equals(user.getName()))
            {
                result.add(board);
            }
        }

        return result;
    }

    @Override
    public Board getBoardByName(String name)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
