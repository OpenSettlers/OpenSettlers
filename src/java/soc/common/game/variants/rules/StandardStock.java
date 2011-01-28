package soc.common.game.variants.rules;

import soc.common.game.variants.GameRules;

/*
 * Uses standard amount of stock items: 4 cities, 5 towns, 15 roads
 */
public class StandardStock implements GameRule
{

    @Override
    public String getDescription()
    {
        // TODO fix message
        return null;
    }

    @Override
    public void set(GameRules rules)
    {
        rules.setStockCityAmount(4);
        rules.setStockRoadAmount(15);
        rules.setStockTownAmount(5);
    }

}
