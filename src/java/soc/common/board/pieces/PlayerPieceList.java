package soc.common.board.pieces;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import com.google.gwt.event.shared.SimpleEventBus;

public class PlayerPieceList implements Iterable<PlayerPiece>
{
    List<PlayerPiece> playerPieces = new ArrayList<PlayerPiece>();
    SimpleEventBus eventBus = new SimpleEventBus();

    @Override
    public Iterator<PlayerPiece> iterator()
    {
        return playerPieces.iterator();
    }
    
    /*
     * Returns a list of pieces of given type
     */
    public PlayerPieceList ofType(PlayerPiece type)
    {
        PlayerPieceList result = new PlayerPieceList();
        
        for (PlayerPiece piece : this)
        {
            if (piece.getClass() == type.getClass())
                result.add(piece);
        }
        
        return result;
    }
    
    public void add(PlayerPiece piece)
    {
        playerPieces.add(piece);
        eventBus.fireEvent(new PiecesChangedEvent(piece, null));
    }
    
    public void remove(PlayerPiece piece)
    {
        playerPieces.remove(piece);
        eventBus.fireEvent(new PiecesChangedEvent(null, piece));
    }
    
    public int size()
    {
        return playerPieces.size();
    }
}
