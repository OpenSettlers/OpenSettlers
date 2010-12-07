package soc.common.board.hexes;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

import soc.common.annotations.SeaFarers;
import soc.common.board.territories.Territory;

@SeaFarers
public interface ITerritoryHex
{
    public Territory getTerritory();
    public ITerritoryHex setTerritory(Territory territory);
    public HandlerRegistration addTerritoryChangedEventHandler(TerritoryChangedEventHandler handler);
}
