package org.soc.common.game.variants.rules;

import org.soc.common.game.dices.StandardDice;
import org.soc.common.game.variants.GameRules;

public class NormalDices implements GameRule
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
        rules.setDiceType(new StandardDice());
    }
}