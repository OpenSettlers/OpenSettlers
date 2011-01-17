package soc.common.board.pieces;

import soc.common.board.HexPoint;
import soc.common.board.hexes.ResourceHex;
import soc.common.board.resources.ResourceList;
import soc.common.game.GameRules;

public interface Producable
{
    public ResourceList produce(ResourceHex resourceHex, GameRules rules);

    public HexPoint getPoint();
}
