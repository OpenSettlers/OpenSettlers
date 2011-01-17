package soc.common.board.pieces;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.event.shared.GwtEvent;

public class PlistChangedEvent<P> extends GwtEvent<PlistChangedEventHandler<P>>
{
    private static Map<Class<?>, Type<PlistChangedEventHandler<?>>> TYPES = new HashMap<Class<?>, Type<PlistChangedEventHandler<?>>>();
    private P addedPiece;
    private P removedPiece;

    public static Type<PlistChangedEventHandler<?>> getType(Class<?> clazz)
    {
        if (!TYPES.containsKey(clazz))
            TYPES.put(clazz, new Type<PlistChangedEventHandler<?>>());

        return TYPES.get(clazz);
    }

    public PlistChangedEvent(P addedPiece, P removedPiece)
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
    protected void dispatch(PlistChangedEventHandler<P> handler)
    {
        handler.onPlistChanged(this);
    }

    @Override
    public Type<PlistChangedEventHandler<P>> getAssociatedType()
    {
        return (Type) PlistChangedEvent.getType(getChangedPiece().getClass());
    }

}
