package soc.common.board.pieces;

import com.google.gwt.event.shared.HandlerRegistration;

public class CityList extends Plist<City>
{
    public HandlerRegistration addCitiesChangedEventHandler(
            PlistChangedEventHandler<City> handler)
    {
        return eventBus.addHandler(PlistChangedEvent.getType(City.class),
                handler);
    }
}
