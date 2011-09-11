package org.soc.gwt.client.main;

import java.util.List;

import org.soc.common.game.board.Board;
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
    public Board byId(String id)
    {
        Board result = null;

        result = boards.byId(id);
        if (result == null)
        {
            // try to get the board from browser storage
        }

        // try to get the browser from a server

        return null;
    }

    @Override
    public List<Board> all()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Board> byUser(org.soc.common.server.entities.User user)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Board byName(String name)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
