package soc.common.game.variants.rules;

import soc.common.game.dices.CitiesKnightsDice;
import soc.common.game.variants.GameRules;

public class UseCitiesKnightsDice implements GameRule
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
        rules.setDiceType(new CitiesKnightsDice());
    }

}
