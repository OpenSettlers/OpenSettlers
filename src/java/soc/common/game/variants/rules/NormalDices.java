package soc.common.game.variants.rules;

import soc.common.game.GameRules;
import soc.common.game.dices.StandardDice;

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
