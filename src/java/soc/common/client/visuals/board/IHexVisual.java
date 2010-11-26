package soc.common.client.visuals.board;

import soc.common.board.hexes.Hex;
import soc.common.client.visuals.IPieceVisual;

public interface IHexVisual extends IPieceVisual
{
    public IHexVisual setHex(Hex hex);
    public Hex getHex();
    public ITerritoryVisual getTerritory();
    public IChitVisual getChitVisual();
    public IPortPossibilitiesVisual getPortPossibilitiesVisual();
    public IBoardVisual getParent();
}
