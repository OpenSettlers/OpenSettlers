package org.soc.common.game.developmentCards;

import com.google.gwt.event.shared.GwtEvent;

public class DevelopmentCardsChangedEvent extends
        GwtEvent<DevelopmentCardsChangedEventHandler>
{
    public static Type<DevelopmentCardsChangedEventHandler> TYPE = new Type<DevelopmentCardsChangedEventHandler>();
    private DevelopmentCard removedCard;
    private DevelopmentCard addedCard;

    /**
     * @return the removedCard
     */
    public DevelopmentCard getRemovedCard()
    {
        return removedCard;
    }

    /**
     * @return the addedCard
     */
    public DevelopmentCard getAddedCard()
    {
        return addedCard;
    }

    public DevelopmentCardsChangedEvent(DevelopmentCard addedCard,
            DevelopmentCard removedCard)
    {
        super();
        this.addedCard = addedCard;
        this.removedCard = removedCard;
    }

    @Override
    protected void dispatch(DevelopmentCardsChangedEventHandler handler)
    {
        handler.onDevelopmentCardsChanged(this);
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<DevelopmentCardsChangedEventHandler> getAssociatedType()
    {
        return TYPE;
    }

}
