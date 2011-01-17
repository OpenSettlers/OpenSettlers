package soc.common.board.pieces;

import soc.common.board.HexPoint;

import com.google.gwt.event.shared.HandlerRegistration;

public class TownList extends Plist<Town>
{
    public HandlerRegistration addTownsChangedEventHandler(
            PlistChangedEventHandler<Town> handler)
    {
        return eventBus.addHandler(PlistChangedEvent.getType(Town.class),
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
