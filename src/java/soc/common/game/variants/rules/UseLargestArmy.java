package soc.common.game.variants.rules;

import soc.common.board.pieces.Army;
import soc.common.game.GameRules;

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
