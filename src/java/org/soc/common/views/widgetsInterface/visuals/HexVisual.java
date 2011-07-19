package org.soc.common.views.widgetsInterface.visuals;

import org.soc.common.board.hexes.ChitChangedEventHandler;
import org.soc.common.board.hexes.Hex;
import org.soc.common.board.hexes.TerritoryChangedEventHandler;
import org.soc.common.board.ports.PortChangedEventHandler;

public interface HexVisual extends PieceVisual, ChitChangedEventHandler,
        TerritoryChangedEventHandler, PortChangedEventHandler
{
    public HexVisual setHex(Hex hex);

    public HexVisual setDarkened(boolean darkened);

    public Hex getHex();

    public TerritoryVisual getTerritory();

    public ChitVisual getChitVisual();

    public PortPossibilitiesVisual getPortPossibilitiesVisual();

    public BoardVisual getParent();
}
