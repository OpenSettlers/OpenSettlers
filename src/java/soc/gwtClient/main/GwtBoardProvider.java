package soc.gwtClient.main;

import java.util.List;

import soc.common.board.Board;
import soc.common.game.User;
import soc.server.data.BoardList;
import soc.server.data.IBoardProvider;

/*
 * Provider of Boards for gwt client. Checks memory instances, then local browser cache, then asks a server
 * for specified boards
 */
public class GwtBoardProvider implements IBoardProvider
{
    BoardList boards = new BoardList();
    
    @Override
    public Board getBoardByID(String id)
    {
        Board result = null;
        
        result=boards.getBoardByID(id);
        if (result == null)
        {
            // try to get the board from browser storage
        }
        
        // try to get the browser from a server 
        
        return null;
    }

    @Override
    public Board getBoardByName(String Name)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Board> getBoardsFromUser(User user)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Board> getAllBoards()
    {
        // TODO Auto-generated method stub
        return null;
    }
    

}
