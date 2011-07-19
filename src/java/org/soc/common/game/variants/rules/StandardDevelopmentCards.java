package org.soc.common.game.variants.rules;

import org.soc.common.game.developmentCards.DevelopmentCardList;
import org.soc.common.game.variants.GameRules;

public class StandardDevelopmentCards implements GameRule
{

    @Override
    public String getDescription()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void set(GameRules rules)
    {
        rules.setDevelopmentCardStack(DevelopmentCardList.standard());
    }

}
