package soc.common.game.variants;

import java.util.ArrayList;
import java.util.List;

import soc.common.game.Game;
import soc.common.game.variants.rules.GameRule;
import soc.common.game.variants.rules.NoLargestArmy;
import soc.common.game.variants.rules.UseCitiesKnightsDice;
import soc.common.game.variants.rules.UseWalls;

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
