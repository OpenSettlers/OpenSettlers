package soc.common.board.routing;

import soc.common.annotations.SeaFarers;
import soc.common.board.territories.Territory;
import soc.common.game.VictoryPointItem;

/*
 * Represents a route between two cities and/or towns on two different territories.
 */
@SeaFarers
public interface TradeRoute extends Route, VictoryPointItem
{
    public Territory getFromTerritory();

    public Territory getDestinationTerritory();
}
