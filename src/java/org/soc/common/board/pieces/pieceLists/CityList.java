package org.soc.common.board.pieces.pieceLists;

import java.io.Serializable;

import org.soc.common.board.pieces.City;


import com.google.gwt.event.shared.HandlerRegistration;

public class CityList extends PlayerPieceList<City> implements Serializable
{
    private static final long serialVersionUID = -8814283813518513594L;

    public HandlerRegistration addCitiesChangedEventHandler(
            PlayerPieceListChangedEventHandler<City> handler)
    {
        return eventBus.addHandler(PlayerPieceListChangedEvent
                .getType(City.class), handler);
    }
}
