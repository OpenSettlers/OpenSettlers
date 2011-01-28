package soc.common.game.variants.rules;

import soc.common.game.developmentCards.DevelopmentCardList;
import soc.common.game.variants.GameRules;

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
