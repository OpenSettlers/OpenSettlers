package org.soc.common.board.pieces.abstractPieces;

import org.soc.common.board.hexes.Hex;
import org.soc.common.board.layout.HexPoint;
import org.soc.common.board.resources.ResourceList;
import org.soc.common.game.variants.GameRules;

public interface Producable
{
    public ResourceList produce(Hex hex, GameRules rules);

    public HexPoint getPoint();
}
