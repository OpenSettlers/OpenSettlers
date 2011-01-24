package soc.common.game;

import soc.common.board.routing.Route;
import soc.common.game.player.GamePlayer;

import com.google.gwt.event.shared.GwtEvent;

public class LongestRoadChangedEvent extends
        GwtEvent<LongestRoadChangedEventHandler>
{
    public static Type<LongestRoadChangedEventHandler> TYPE = new Type<LongestRoadChangedEventHandler>();
    private Route oldRoute;
    private Route newRoute;

    public LongestRoadChangedEvent(Route oldRoute, Route newRoute)
    {
        super();
        this.oldRoute = oldRoute;
        this.newRoute = newRoute;
    }

    public GamePlayer getOldOwner()
    {
        return oldRoute.getPlayer();
    }

    public GamePlayer getNewOwner()
    {
        return newRoute.getPlayer();
    }

    public Route getOldRoute()
    {
        return oldRoute;
    }

    public Route getNewRoute()
    {
        return newRoute;
    }

    /*
     * Returns true when either oldOwner or newOwner is null, or when oldOwner
     * does not equal newOwner
     */
    public boolean isOwnerChanged()
    {
        if (getOldOwner() == null || getNewOwner() == null)
            return true;

        return (getOldOwner().equals(getNewOwner()));
    }

    @Override
    protected void dispatch(LongestRoadChangedEventHandler handler)
    {
        handler.onLongestRoadChanged(this);
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<LongestRoadChangedEventHandler> getAssociatedType()
    {
        return TYPE;
    }

}
