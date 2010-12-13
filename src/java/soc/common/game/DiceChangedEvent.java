package soc.common.game;

import soc.common.game.dices.IDice;

import com.google.gwt.event.shared.GwtEvent;

public class DiceChangedEvent extends GwtEvent<DiceChangedEventHandler>
{
    public static Type<DiceChangedEventHandler> TYPE = new Type<DiceChangedEventHandler>();
    private IDice newDice;
    
    public DiceChangedEvent(IDice newDice)
    {
        super();
        this.newDice = newDice;
    }

    /**
     * @return the newDice
     */
    public IDice getNewDice()
    {
        return newDice;
    }

    @Override
    protected void dispatch(DiceChangedEventHandler arg0)
    {
        arg0.onDiceChanged(this);
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<DiceChangedEventHandler> getAssociatedType()
    {
        return TYPE;
    }

}
