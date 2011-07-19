package org.soc.gwt.client.main;

import java.util.List;

import org.soc.common.board.Board;
import org.soc.common.server.entities.BoardList;
import org.soc.common.server.entities.BoardProvider;


/*
 * Provider of Boards for gwt client. Checks memory instances, then local browser cache, then asks a server
 * for specified boards
 */
public class GwtBoardProvider implements BoardProvider
{
    BoardList boards = new BoardList();

    @Override
    public Board getBoardByID(String id)
    {
        Board result = null;

        result = boards.getBoardByID(id);
        if (result == null)
        {
            // try to get the board from browser storage
        }

        // try to get the browser from a server

        return null;
    }

    @Override
    public List<Board> getAllBoards()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Board> getBoardsFromUser(org.soc.common.server.entities.User user)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Board getBoardByName(String name)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
