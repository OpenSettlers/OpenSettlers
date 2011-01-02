package soc.common.client.visuals.board;

import soc.common.board.hexes.ChitChangedEventHandler;
import soc.common.board.hexes.Hex;
import soc.common.board.hexes.TerritoryChangedEventHandler;
import soc.common.board.ports.PortChangedEventHandler;
import soc.common.client.visuals.PieceVisual;

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
