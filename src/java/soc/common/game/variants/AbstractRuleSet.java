package soc.common.game.variants;

import java.util.ArrayList;
import java.util.List;

import soc.common.game.Game;
import soc.common.game.GameRules;
import soc.common.game.variants.rules.GameRule;

/*
 * Basic standard settlers ruleset
 */
public abstract class AbstractRuleSet implements Variant
{
    protected Game game;
    protected List<GameRule> rules = new ArrayList<GameRule>();

    /**
     * @return the rules
     */
    public List<GameRule> getRules()
    {
        return rules;
    }

    public AbstractRuleSet(Game game)
    {
        this.game = game;
    }

    @Override
    public void setRules(GameRules gameRules)
    {
        for (GameRule rule : rules)
        {
            rule.set(gameRules);
        }
    }
}
