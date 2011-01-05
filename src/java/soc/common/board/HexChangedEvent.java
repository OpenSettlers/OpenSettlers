package soc.common.board;

import soc.common.board.hexes.Hex;

import com.google.gwt.event.shared.GwtEvent;

public class HexChangedEvent extends GwtEvent<HexChangedEventHandler>
{
    public static Type<HexChangedEventHandler> TYPE = new Type<HexChangedEventHandler>();
    private Hex oldHex;
    private Hex newHex;

    public HexChangedEvent(Hex oldHex, Hex newHex)
    {
        super();
        this.oldHex = oldHex;
        this.newHex = newHex;
    }

    /**
     * @return the oldHex
     */
    public Hex getOldHex()
    {
        return oldHex;
    }

    /**
     * @return the newHex
     */
    public Hex getNewHex()
    {
        return newHex;
    }

    @Override
    protected void dispatch(HexChangedEventHandler handler)
    {
        handler.onHexChanged(this);
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<HexChangedEventHandler> getAssociatedType()
    {
        return TYPE;
    }

}
