package soc.common.board.pieces;

import com.google.gwt.event.shared.GwtEvent;

public class ArmyChangedEvent extends GwtEvent<ArmyChangedEventHandler>
{
    public static Type<ArmyChangedEventHandler> TYPE = new Type<ArmyChangedEventHandler>();
    private int newSoldierAmount;
    private boolean isLargest;
    private boolean wasLargest;
    private int oldSoldierAmount;

    public ArmyChangedEvent(int newSoldierAmount, boolean isLargest,
            boolean wasLargest, int oldSoldierAmount)
    {
        super();
        this.newSoldierAmount = newSoldierAmount;
        this.isLargest = isLargest;
        this.wasLargest = wasLargest;
        this.oldSoldierAmount = oldSoldierAmount;
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
