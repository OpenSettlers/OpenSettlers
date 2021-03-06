package org.soc.common.game.pieces;

import java.io.Serializable;

import org.soc.common.game.board.HexPoint;

import com.google.gwt.event.shared.HandlerRegistration;

public class TownList extends PlayerPieceList<Town> implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = -2324707060822969303L;

    public HandlerRegistration addTownsChangedEventHandler(
            PlayerPieceListChangedEventHandler<Town> handler)
    {
        return eventBus.addHandler(PlayerPieceListChangedEvent
                .getType(Town.class), handler);
    }

    public Town get(HexPoint pointLocation)
    {
        for (Town town : pieces)
        {
            if (town.point().equals(pointLocation))
            {
                return town;
            }
        }

        throw new AssertionError("Expected town with specified HexPoint");
    }

    public boolean contains(HexPoint point)
    {
        for (Town town : pieces)
            if (town.point().equals(point))
                return true;

        return false;
    }
}
