package soc.common.game.variants.rules;

import soc.common.annotations.CitiesKnights;
import soc.common.game.GameRules;

/*
 * Do not use a largest army
 */
@CitiesKnights
public class NoLargestArmy implements GameRule
{
    /*
     * Removes the LargestArmy if found
     * 
     * @see soc.common.game.variants.GameRule#add(soc.common.game.GameRules)
     */
    @Override
    public void set(GameRules rules)
    {
        rules.setLargestArmy(null);
    }

    @Override
    public String getDescription()
    {
        // TODO fix message
        return null;
    }

}
