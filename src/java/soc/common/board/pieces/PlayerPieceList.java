package soc.common.board.pieces;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import soc.common.board.HexLocation;
import soc.common.board.HexPoint;
import soc.common.board.HexSide;
import sun.nio.ch.SocketOpts.IP;


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
    public PlayerPieceList ofType(Class clazz)
    {
        PlayerPieceList result = new PlayerPieceList();
        
        for (PlayerPiece piece : this)
        {
            if (piece.getClass().isInstance(clazz))
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

    /*
     * Returns true if this collection contains an ISidePiece with given HexPoint
     */
    public boolean contains(HexPoint point)
    {
        for (PlayerPiece piece : playerPieces)
        {
            if (piece instanceof IPointPiece)
            {
                IPointPiece pointPiece = (IPointPiece)piece;
                if (pointPiece.getPoint().equals(point))
                    return true;
            }
        }
        return false;
    }

    public boolean contains(HexSide hexSide)
    {
        for (PlayerPiece piece : playerPieces)
        {
            if (piece instanceof ISidePiece)
            {
                ISidePiece pointPiece = (ISidePiece)piece;
                if (pointPiece.getSide().equals(hexSide))
                    return true;
            }
        }
        return false;
    }

    public PlayerPiece remove(HexPoint pointLocation)
    {
        PlayerPiece playerPieceToRemove = null;
        for (PlayerPiece playerPiece : playerPieces)
        {
            if (playerPiece instanceof IPointPiece)
            {
                IPointPiece pointPieze = (IPointPiece)playerPiece;
                if (pointPieze.getPoint().equals(pointLocation))
                {
                    playerPieceToRemove = playerPiece;
                }
            }
        }
        if (playerPieceToRemove !=null)
        {
            playerPieces.remove(playerPieceToRemove);
        }
        else
        {
            throw new RuntimeException("Tried to remove a PointPiece not contained in this list");
        }
        
        return playerPieceToRemove;
    }
    
    public PlayerPiece remove(HexSide side)
    {
        PlayerPiece playerPieceToRemove = null;
        for (PlayerPiece playerPiece : playerPieces)
        {
            if (playerPiece instanceof IPointPiece)
            {
                ISidePiece sidePieze = (ISidePiece)playerPiece;
                if (sidePieze.getSide().equals(side))
                {
                    playerPieceToRemove = playerPiece;
                }
            }
        }
        if (playerPieceToRemove !=null)
        {
            playerPieces.remove(playerPieceToRemove);
        }
        else
        {
            throw new RuntimeException("Tried to remove a PointPiece not contained in this list");
        }
        
        return playerPieceToRemove;
    }

    public PlayerPiece get(Class clazz)
    {
        for (PlayerPiece playerPiece : playerPieces)
        {
            if (playerPiece.getClass().isInstance(clazz))
            {
                return playerPiece;
            }
        }
        throw new RuntimeException("Tried to get a piece which is not contained in the list");
    }
}
