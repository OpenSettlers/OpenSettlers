package soc.common.board.pieces.abstractPieces;

import soc.common.board.HexPoint;
import soc.common.board.hexes.Hex;
import soc.common.board.resources.ResourceList;
import soc.common.game.variants.GameRules;

public interface Producable
{
    public ResourceList produce(Hex hex, GameRules rules);

    public HexPoint getPoint();
}
