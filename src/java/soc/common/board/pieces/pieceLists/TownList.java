package soc.common.board.pieces.pieceLists;

import soc.common.board.HexPoint;
import soc.common.board.pieces.Town;

import com.google.gwt.event.shared.HandlerRegistration;

public class TownList extends PlayerPieceList<Town>
{
    public HandlerRegistration addTownsChangedEventHandler(
            PlayerPieceListChangedEventHandler<Town> handler)
    {
        return eventBus.addHandler(PlayerPieceListChangedEvent.getType(Town.class),
                handler);
    }

    public Town get(HexPoint pointLocation)
    {
        for (Town town : pieces)
        {
            if (town.getPoint().equals(pointLocation))
            {
                return town;
            }
        }

        throw new AssertionError("Expected town with specified HexPoint");
    }

    public boolean contains(HexPoint point)
    {
        for (Town town : pieces)
            if (town.getPoint().equals(point))
                return true;

        return false;
    }
}