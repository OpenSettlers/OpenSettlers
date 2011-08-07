package org.soc.common.game.variants.rules;

import org.soc.common.game.variants.GameRules;

public interface GameRule
{
    public String getDescription();

    public void set(GameRules rules);
}
