package soc.common.board.pieces;

import soc.common.game.developmentCards.standard.Soldier;

import com.google.gwt.event.shared.GwtEvent;

public class ArmyChangedEvent extends GwtEvent<ArmyChangedEventHandler>
{
    public static Type<ArmyChangedEventHandler> TYPE = new Type<ArmyChangedEventHandler>();
    private int newSoldierAmount;
    private boolean isLargest;
    private boolean wasLargest;
    private int oldSoldierAmount;
    private Soldier addedSoldier;
    private Soldier removedSoldier;

    public ArmyChangedEvent(int newSoldierAmount, boolean isLargest,
            boolean wasLargest, int oldSoldierAmount, Soldier removedSoldier,
            Soldier addedSoldier)
    {
        super();
        this.newSoldierAmount = newSoldierAmount;
        this.isLargest = isLargest;
        this.wasLargest = wasLargest;
        this.oldSoldierAmount = oldSoldierAmount;
        this.removedSoldier = removedSoldier;
        this.addedSoldier = addedSoldier;
    }

    /**
     * @return the newSoldierAmount
     */
    public int getNewSoldierAmount()
    {
        return newSoldierAmount;
    }

    /**
     * @return the isLargest
     */
    public boolean isLargest()
    {
        return isLargest;
    }

    /**
     * @return the wasLargest
     */
    public boolean isWasLargest()
    {
        return wasLargest;
    }

    /**
     * @return the oldSoldierAmount
     */
    public int getOldSoldierAmount()
    {
        return oldSoldierAmount;
    }

    /**
     * @return the addedSoldier
     */
    public Soldier getAddedSoldier()
    {
        return addedSoldier;
    }

    /**
     * @return the removedSoldier
     */
    public Soldier getRemovedSoldier()
    {
        return removedSoldier;
    }

    @Override
    protected void dispatch(ArmyChangedEventHandler handler)
    {
        handler.onArmyChanged(this);
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<ArmyChangedEventHandler> getAssociatedType()
    {
        return TYPE;
    }

}
