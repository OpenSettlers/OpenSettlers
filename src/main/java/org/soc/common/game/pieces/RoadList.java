package org.soc.common.game.pieces;

import java.io.Serializable;

import org.soc.common.game.board.HexSide;

import com.google.gwt.event.shared.HandlerRegistration;

public class RoadList extends PlayerPieceList<Road> implements Serializable
{
    private static final long serialVersionUID = 434233437868739237L;

    public HandlerRegistration addRoadsChangedEventHandler(
            PlayerPieceListChangedEventHandler<Road> handler)
    {
        return eventBus.addHandler(PlayerPieceListChangedEvent
                .getType(Road.class), handler);
    }

    public boolean contains(HexSide side)
    {
        for (Road road : pieces)
            if (road.getSide().equals(side))
                return true;

        return false;
    }
}
