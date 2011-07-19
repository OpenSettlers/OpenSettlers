package org.soc.common.board.routing;

import org.soc.common.annotations.SeaFarers;
import org.soc.common.board.territories.Territory;
import org.soc.common.game.VictoryPointItem;

/*
 * Represents a route between two cities and/or towns on two different territories.
 */
@SeaFarers
public interface TradeRoute extends Route, VictoryPointItem
{
    public Territory getFromTerritory();

    public Territory getDestinationTerritory();
}
