package org.soc.common.game.variants.rules;

import org.soc.common.board.resources.Clay;
import org.soc.common.board.resources.Ore;
import org.soc.common.board.resources.Sheep;
import org.soc.common.board.resources.Timber;
import org.soc.common.board.resources.Wheat;
import org.soc.common.game.variants.GameRules;

/*
 * Allow basic resources to be played with
 */
public class BasicResources implements GameRule
{

    @Override
    public void set(GameRules rules)
    {
        rules.getSupportedResources().add(new Timber());
        rules.getSupportedResources().add(new Wheat());
        rules.getSupportedResources().add(new Ore());
        rules.getSupportedResources().add(new Clay());
        rules.getSupportedResources().add(new Sheep());
    }

    @Override
    public String getDescription()
    {
        // TODO fix message
        return null;
    }

}
