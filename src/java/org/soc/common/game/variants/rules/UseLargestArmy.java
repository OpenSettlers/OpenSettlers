package org.soc.common.game.variants.rules;

import org.soc.common.board.pieces.Army;
import org.soc.common.game.variants.GameRules;

public class UseLargestArmy implements GameRule
{

    @Override
    public void set(GameRules rules)
    {
        rules.getPlayablePieces().add(new Army());
    }

    @Override
    public String getDescription()
    {
        // TODO fix message
        return null;
    }

}
