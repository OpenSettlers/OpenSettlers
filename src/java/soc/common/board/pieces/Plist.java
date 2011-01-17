package soc.common.board.pieces;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.event.shared.SimpleEventBus;

public class Plist<P extends PlayerPiece> implements Iterable<P>
{
    protected List<P> pieces = new ArrayList<P>();
    protected SimpleEventBus eventBus = new SimpleEventBus();

    public void remove(P piece)
    {
        pieces.remove(piece);
        eventBus.fireEvent(new PlistChangedEvent<P>(null, piece));
    }

    public void add(P piece)
    {
        pieces.add(piece);
        eventBus.fireEvent(new PlistChangedEvent<P>(piece, null));
    }

    public int size()
    {
        return pieces.size();
    }

    public void moveFrom(Plist from, P pieceToMove)
    {
        from.remove(pieceToMove);
        add(pieceToMove);
    }

    public P get(int index)
    {
        return pieces.get(index);
    }

    @Override
    public Iterator<P> iterator()
    {
        return pieces.iterator();
    }
}
