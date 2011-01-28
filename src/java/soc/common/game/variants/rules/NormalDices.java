package soc.common.game.variants.rules;

import soc.common.game.dices.StandardDice;
import soc.common.game.variants.GameRules;

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
