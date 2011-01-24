package soc.common.board.pieces.pieceLists;

import soc.common.board.HexSide;
import soc.common.board.pieces.Road;

import com.google.gwt.event.shared.HandlerRegistration;

public class RoadList extends PlayerPieceList<Road>
{
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
