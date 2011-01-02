package soc.common.board.routing;

import soc.common.annotations.SeaFarers;
import soc.common.board.territories.Territory;
import soc.common.game.VictoryPointItem;

@SeaFarers
public interface TradeRoute extends Route, VictoryPointItem
{
    public Territory getFromTerritory();

    public Territory getDestinationTerritory();
}
