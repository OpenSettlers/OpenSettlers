package org.soc.common.server.entities;

import java.util.ArrayList;
import java.util.List;

import org.soc.common.game.board.Board;
import org.soc.common.game.board.StandardFourPlayer;


public class ConstructorBoardProvider implements BoardProvider
{
    private static List<Board> boards = new ArrayList<Board>();

    static
    {
        boards.add(new StandardFourPlayer());
    }

    @Override
    public List<Board> all()
    {
        return boards;
    }

    @Override
    public Board byId(String id)
    {
        for (Board board : boards)
            if (board.getId().equals(id))
                return board;

        return null;
    }

    @Override
    public Board byName(String name)
    {
        for (Board board : boards)
            if (board.name().equals(name))
                return board;

        return null;
    }

    @Override
    public List<Board> byUser(User user)
    {
        List<Board> boardsDesignedByUser = new ArrayList<Board>();

        for (Board board : boards)
            if (board.getDesigner().equals(user.name()))
                boardsDesignedByUser.add(board);

        return boardsDesignedByUser;
    }
}
