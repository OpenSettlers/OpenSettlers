package soc.common.game.variants;

import java.util.List;

import soc.common.game.variants.rules.GameRule;

public interface Variant
{
    public void setRules(GameRules gameRules);

    public List<GameRule> getRules();
}
