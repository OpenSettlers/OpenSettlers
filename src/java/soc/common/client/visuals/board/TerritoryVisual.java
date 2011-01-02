package soc.common.client.visuals.board;

import soc.common.board.territories.Territory;
import soc.common.client.visuals.PieceVisual;

public interface TerritoryVisual extends PieceVisual
{
    public TerritoryVisual setTerritory(Territory territory);
}
