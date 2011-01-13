package soc.common.board.pieces;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import soc.common.board.HexPoint;
import soc.common.board.HexSide;

import com.google.gwt.event.shared.SimpleEventBus;

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

    public PlayerPiece get(int index)
    {
        return playerPieces.get(index);
    }

    /*
     * Returns a list of pieces of given type based on class equality
     */
    public PlayerPieceList ofType(PlayerPiece type)
    {
        PlayerPieceList result = new PlayerPieceList();

        for (PlayerPiece piece : playerPieces)
        {
            if (piece.getClass() == type.getClass())
                result.add(piece);
        }

        return result;
    }

    public PlayerPieceList getSidePieces()
    {
        PlayerPieceList result = new PlayerPieceList();

        for (PlayerPiece piece : playerPieces)
        {
            if (piece instanceof SidePiece)
                result.add(piece);
        }

        return result;
    }

    public PlayerPieceList getPointPieces()
    {
        PlayerPieceList result = new PlayerPieceList();

        for (PlayerPiece piece : playerPieces)
        {
            if (piece instanceof PointPiece)
                result.add(piece);
        }

        return result;
    }

    public void add(PlayerPiece piece)
    {
        playerPieces.add(piece);
        safelyFireEvent(new PiecesChangedEvent(piece, null));
    }

    public void add(PlayerPieceList pieces)
    {
        for (PlayerPiece playerPiece : pieces)
        {
            playerPieces.add(playerPiece);
        }
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
     * Returns true if this collection contains an ISidePiece with given
     * HexPoint
     */
    public boolean contains(HexPoint point)
    {
        for (PlayerPiece piece : playerPieces)
        {
            if (piece instanceof PointPiece)
            {
                PointPiece pointPiece = (PointPiece) piece;
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
            if (piece instanceof SidePiece)
            {
                SidePiece pointPiece = (SidePiece) piece;
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
            if (playerPiece instanceof PointPiece)
            {
                PointPiece pointPieze = (PointPiece) playerPiece;
                if (pointPieze.getPoint().equals(pointLocation))
                {
                    playerPieceToRemove = playerPiece;
                }
            }
        }
        if (playerPieceToRemove != null)
        {
            remove(playerPieceToRemove);
        }
        else
        {
            throw new RuntimeException(
                    "Tried to remove a PointPiece not contained in this list");
        }

        return playerPieceToRemove;
    }

    public PlayerPiece remove(HexSide side)
    {
        PlayerPiece playerPieceToRemove = null;
        for (PlayerPiece playerPiece : playerPieces)
        {
            if (playerPiece instanceof PointPiece)
            {
                SidePiece sidePieze = (SidePiece) playerPiece;
                if (sidePieze.getSide().equals(side))
                {
                    playerPieceToRemove = playerPiece;
                }
            }
        }
        if (playerPieceToRemove != null)
        {
            remove(playerPieceToRemove);
        }
        else
        {
            throw new RuntimeException(
                    "Tried to remove a PointPiece not contained in this list");
        }

        return playerPieceToRemove;
    }

    public PlayerPiece get(PlayerPiece pieceType)
    {
        for (PlayerPiece playerPiece : playerPieces)
        {
            if (playerPiece.getClass() == pieceType.getClass())
            {
                return playerPiece;
            }
        }
        throw new RuntimeException(
                "Tried to get a piece which is not contained in the list");
    }

    public PlayerPiece get(HexPoint point)
    {
        for (PlayerPiece playerPiece : getPointPieces())
        {
            PointPiece pointPiece = (PointPiece) playerPiece;
            if (pointPiece.getPoint().equals(point))
            {
                return playerPiece;
            }
        }

        return null;
    }

    public static void move(PlayerPiece pieceToMove, PlayerPieceList from,
            PlayerPieceList to)
    {
        from.remove(pieceToMove);
        to.add(pieceToMove);
    }
}
