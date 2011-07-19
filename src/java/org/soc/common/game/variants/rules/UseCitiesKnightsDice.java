package org.soc.common.game.variants.rules;

import org.soc.common.game.dices.CitiesKnightsDice;
import org.soc.common.game.variants.GameRules;

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
