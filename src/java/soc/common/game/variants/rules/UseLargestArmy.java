package soc.common.game.variants.rules;

import soc.common.board.pieces.LargestArmy;
import soc.common.game.GameRules;

public class UseLargestArmy implements GameRule
{

    @Override
    public void set(GameRules rules)
    {
        rules.getPlayablePieces().add(new LargestArmy());
    }

    @Override
    public String getDescription()
    {
        // TODO fix message
        return null;
    }

}
