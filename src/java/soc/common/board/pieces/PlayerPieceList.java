package soc.common.board.pieces;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.event.shared.GwtEvent.Type;

public class PlayerPieceList implements Iterable<PlayerPiece>
{
    List<PlayerPiece> playerPieces = new ArrayList<PlayerPiece>();
    SimpleEventBus eventBus;
    
    private void safelyFireEvent(PiecesChangedEvent event)
    {
        if (eventBus != null)
        {
            eventBus.fireEvent(event);
        }
    }

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
        safelyFireEvent(new PiecesChangedEvent(piece, null));
    }
    
    public void remove(PlayerPiece piece)
    {
        playerPieces.remove(piece);
        safelyFireEvent(new PiecesChangedEvent(null, piece));
    }
    
    public int size()
    {
        return playerPieces.size();
    }
    
    public void addPiecesChangedEventHandler(PiecesChangedEventHandler handler)
    {
        getEventBus().addHandler(PiecesChangedEvent.TYPE, handler);
    }
    
    private SimpleEventBus getEventBus()
    {
        if (eventBus == null)
        {
            eventBus = new SimpleEventBus();
        }
        
        return eventBus;
    }
}
