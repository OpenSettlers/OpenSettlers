package soc.common.client.visuals.board;

import soc.common.board.hexes.ChitChangedEventHandler;
import soc.common.board.hexes.AbstractHex;
import soc.common.board.hexes.TerritoryChangedEventHandler;
import soc.common.board.ports.PortChangedEventHandler;
import soc.common.client.visuals.IPieceVisual;

public interface IHexVisual extends IPieceVisual, 
    ChitChangedEventHandler, TerritoryChangedEventHandler, PortChangedEventHandler
{
    public IHexVisual setHex(AbstractHex hex);
    public AbstractHex getHex();
    public ITerritoryVisual getTerritory();
    public IChitVisual getChitVisual();
    public IPortPossibilitiesVisual getPortPossibilitiesVisual();
    public IBoardVisual getParent();
}
