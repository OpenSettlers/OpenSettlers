package soc.common.board.hexes;

import soc.common.board.Chit;

import com.google.gwt.event.shared.GwtEvent;

public class ChitChangedEvent extends GwtEvent<ChitChangedEventHandler>
{
    public static Type<ChitChangedEventHandler> TYPE = new Type<ChitChangedEventHandler>();
    private Chit chit;
    
    public ChitChangedEvent(Chit c)
    {
        this.chit=c;
    }
    /**
     * @return the chit
     */
    public Chit getChit()
    {
        return chit;
    }

    @Override
    protected void dispatch(ChitChangedEventHandler handler)
    {
        handler.onChitChanged(this);
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<ChitChangedEventHandler> getAssociatedType()
    {
        return TYPE;
    }

}
