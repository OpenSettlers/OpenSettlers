package soc.common.client.visuals.board;

import soc.common.board.hexes.ChitChangedEventHandler;
import soc.common.board.hexes.Hex;
import soc.common.board.hexes.TerritoryChangedEventHandler;
import soc.common.board.ports.PortChangedEventHandler;
import soc.common.client.visuals.IPieceVisual;

public interface HexVisual extends IPieceVisual, ChitChangedEventHandler,
        TerritoryChangedEventHandler, PortChangedEventHandler
{
    public HexVisual setHex(Hex hex);

    public Hex getHex();

    public ITerritoryVisual getTerritory();

    public IChitVisual getChitVisual();

    public IPortPossibilitiesVisual getPortPossibilitiesVisual();

    public IBoardVisual getParent();
}
