package soc.common.game.variants.rules;

import soc.common.board.resources.Clay;
import soc.common.board.resources.Ore;
import soc.common.board.resources.Sheep;
import soc.common.board.resources.Timber;
import soc.common.board.resources.Wheat;
import soc.common.game.variants.GameRules;

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
