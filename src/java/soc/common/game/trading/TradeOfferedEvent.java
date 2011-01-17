package soc.common.game.trading;

import soc.common.actions.gameAction.turnActions.standard.TradeOffer;

import com.google.gwt.event.shared.GwtEvent;

public class TradeOfferedEvent extends GwtEvent<TradeOfferedEventHandler>
{
    public static Type<TradeOfferedEventHandler> TYPE = new Type<TradeOfferedEventHandler>();
    private TradeOffer tradeOffer;

    public TradeOfferedEvent(TradeOffer tradeOffer)
    {
        super();
        this.tradeOffer = tradeOffer;
    }

    /**
     * @return the tradeOffer
     */
    public TradeOffer getTradeOffer()
    {
        return tradeOffer;
    }

    @Override
    protected void dispatch(TradeOfferedEventHandler handler)
    {
        handler.onTradeOffered(this);
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<TradeOfferedEventHandler> getAssociatedType()
    {
        return TYPE;
    }

}
