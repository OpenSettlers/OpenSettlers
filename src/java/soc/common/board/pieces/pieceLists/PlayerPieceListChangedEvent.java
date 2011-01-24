package soc.common.board.pieces.pieceLists;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.event.shared.GwtEvent;

public class PlayerPieceListChangedEvent<P> extends GwtEvent<PlayerPieceListChangedEventHandler<P>>
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

    @Override
    protected void dispatch(PlayerPieceListChangedEventHandler<P> handler)
    {
        handler.onPlayerPieceListChanged(this);
    }

    @Override
    public Type<PlayerPieceListChangedEventHandler<P>> getAssociatedType()
    {
        return (Type) PlayerPieceListChangedEvent.getType(getChangedPiece().getClass());
    }

}
