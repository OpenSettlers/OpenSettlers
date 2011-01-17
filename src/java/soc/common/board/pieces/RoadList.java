package soc.common.board.pieces;

import com.google.gwt.event.shared.HandlerRegistration;

public class RoadList extends Plist<Road>
{
    public HandlerRegistration addRoadsChangedEventHandler(
            PlistChangedEventHandler<Road> handler)
    {
        return eventBus.addHandler(PlistChangedEvent.getType(Road.class),
                handler);
    }
}
