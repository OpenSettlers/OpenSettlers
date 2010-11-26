package soc.common.client.visuals.board;

import soc.common.board.Territory;
import soc.common.client.visuals.IPieceVisual;

public interface ITerritoryVisual extends IPieceVisual
{
    public ITerritoryVisual setTerritory(Territory territory);
}
