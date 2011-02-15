package soc.common.server.entities;

import java.util.ArrayList;
import java.util.List;

import soc.common.board.Board;
import soc.common.board.boards.StandardFourPlayer;

public class ConstructorBoardProvider implements BoardProvider
{
    private static List<Board> boards = new ArrayList<Board>();

    static
    {
        boards.add(new StandardFourPlayer());
    }

    @Override
    public List<Board> getAllBoards()
    {
        return boards;
    }

    @Override
    public Board getBoardByID(String id)
    {
        for (Board board : boards)
            if (board.getBoardSettings().getId().equals(id))
                return board;

        return null;
    }

    @Override
    public Board getBoardByName(String name)
    {
        for (Board board : boards)
            if (board.getBoardSettings().getName().equals(name))
                return board;

        return null;
    }

    @Override
    public List<Board> getBoardsFromUser(User user)
    {
        List<Board> boardsDesignedByUser = new ArrayList<Board>();

        for (Board board : boards)
            if (board.getBoardSettings().getDesigner().equals(user.getName()))
                boardsDesignedByUser.add(board);

        return boardsDesignedByUser;
    }
}
