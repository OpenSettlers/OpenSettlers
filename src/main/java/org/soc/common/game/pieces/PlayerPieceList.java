package org.soc.common.game.pieces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.soc.common.game.pieces.Piece.PlayerPiece;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.SimpleEventBus;

public class PlayerPieceList<P extends PlayerPiece> implements Iterable<P>
{
  protected List<P> pieces = new ArrayList<P>();
  protected SimpleEventBus eventBus = new SimpleEventBus();

  public void remove(P piece)
  {
    pieces.remove(piece);
    eventBus.fireEvent(new PlayerPieceListChangedEvent<P>(null, piece));
  }
  public void add(P piece)
  {
    pieces.add(piece);
    eventBus.fireEvent(new PlayerPieceListChangedEvent<P>(piece, null));
  }
  public int size()
  {
    return pieces.size();
  }
  public void moveFrom(PlayerPieceList from, P pieceToMove)
  {
    from.remove(pieceToMove);
    add(pieceToMove);
  }
  public P get(int index)
  {
    return pieces.get(index);
  }
  @Override public Iterator<P> iterator()
  {
    return pieces.iterator();
  }

  public interface PlayerPieceListChangedEventHandler<P> extends EventHandler
  {
    public void onPlayerPieceListChanged(PlayerPieceListChangedEvent<P> event);
  }

  public static class PlayerPieceListChangedEvent<P> extends
          GwtEvent<PlayerPieceListChangedEventHandler<P>>
  {
    private static Map<Class<?>, Type<PlayerPieceListChangedEventHandler<?>>> TYPES = new HashMap<Class<?>, Type<PlayerPieceListChangedEventHandler<?>>>();
    private P addedPiece;
    private P removedPiece;

    public static Type<PlayerPieceListChangedEventHandler<?>> getType(Class<?> clazz)
    {
      if (!TYPES.containsKey(clazz))
        TYPES.put(clazz, new Type<PlayerPieceListChangedEventHandler<?>>());
      return TYPES.get(clazz);
    }
    public PlayerPieceListChangedEvent(P addedPiece, P removedPiece)
    {
      super();
      this.addedPiece = addedPiece;
      this.removedPiece = removedPiece;
    }
    public P getAddedPiece()
    {
      return addedPiece;
    }
    public P getRemovedPiece()
    {
      return removedPiece;
    }
    public P getChangedPiece()
    {
      return removedPiece == null ? addedPiece : removedPiece;
    }
    @Override protected void dispatch(PlayerPieceListChangedEventHandler<P> handler)
    {
      handler.onPlayerPieceListChanged(this);
    }
    @Override public Type<PlayerPieceListChangedEventHandler<P>> getAssociatedType()
    {
      return (Type) PlayerPieceListChangedEvent.getType(getChangedPiece().getClass());
    }
  }
}
