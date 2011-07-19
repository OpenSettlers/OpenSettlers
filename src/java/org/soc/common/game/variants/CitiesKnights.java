package org.soc.common.game.variants;

import java.util.ArrayList;
import java.util.List;

import org.soc.common.game.Game;
import org.soc.common.game.variants.rules.GameRule;
import org.soc.common.game.variants.rules.NoLargestArmy;
import org.soc.common.game.variants.rules.UseCitiesKnightsDice;
import org.soc.common.game.variants.rules.UseWalls;


public class CitiesKnights extends AbstractRuleSet
{
    private List<GameRule> rules = new ArrayList<GameRule>();

    public CitiesKnights(Game game)
    {
        super(game);

        rules.add(new NoLargestArmy());
        rules.add(new UseCitiesKnightsDice());
        rules.add(new UseWalls());
    }

}
