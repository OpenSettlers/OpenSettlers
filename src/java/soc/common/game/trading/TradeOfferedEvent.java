package soc.common.game.trading;

import com.google.gwt.event.shared.GwtEvent;

public class TradeOfferedEvent extends GwtEvent<TradeOfferedEventHandler>
{
    public static Type<TradeOfferedEventHandler> TYPE = new Type<TradeOfferedEventHandler>();
    private ITradeOffer tradeOffer;
    
    public TradeOfferedEvent(ITradeOffer tradeOffer)
    {
        super();
        this.tradeOffer = tradeOffer;
    }

    /**
     * @return the tradeOffer
     */
    public ITradeOffer getTradeOffer()
    {
        return tradeOffer;
    }

    @Override
    protected void dispatch(TradeOfferedEventHandler arg0)
    {
        arg0.onTradeOffered(this);
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<TradeOfferedEventHandler> getAssociatedType()
    {
        return TYPE;
    }

}
