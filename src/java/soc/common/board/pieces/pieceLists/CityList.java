package soc.common.board.pieces.pieceLists;

import soc.common.board.pieces.City;

import com.google.gwt.event.shared.HandlerRegistration;

public class CityList extends PlayerPieceList<City>
{
    public HandlerRegistration addCitiesChangedEventHandler(
            PlayerPieceListChangedEventHandler<City> handler)
    {
        return eventBus.addHandler(PlayerPieceListChangedEvent.getType(City.class),
                handler);
    }
}
