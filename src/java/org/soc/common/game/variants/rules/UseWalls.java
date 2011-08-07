package org.soc.common.game.variants.rules;

import org.soc.common.annotations.CitiesKnights;
import org.soc.common.annotations.Pioneers;
import org.soc.common.board.pieces.Wall;
import org.soc.common.game.variants.GameRules;

@CitiesKnights
@Pioneers
public class UseWalls implements GameRule
{
    @Override
    public String getDescription()
    {
        // TODO fix message
        return null;
    }

    @Override
    public void set(GameRules rules)
    {
        rules.getPlayablePieces().add(new Wall());
        rules.setStockWallAmount(3);
    }

}
