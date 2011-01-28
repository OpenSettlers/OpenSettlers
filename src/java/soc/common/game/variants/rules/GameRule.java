package soc.common.game.variants.rules;

import soc.common.game.variants.GameRules;

public interface GameRule
{
    public String getDescription();

    public void set(GameRules rules);
}
