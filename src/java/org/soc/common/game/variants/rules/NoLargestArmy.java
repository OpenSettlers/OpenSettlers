package org.soc.common.game.variants.rules;

import org.soc.common.annotations.CitiesKnights;
import org.soc.common.game.variants.GameRules;

/*
 * Do not use a largest army
 */
@CitiesKnights
public class NoLargestArmy implements GameRule
{
    /*
     * Removes the LargestArmy if found
     * 
     * @see org.soc.common.game.variants.GameRule#add(org.soc.common.game.GameRules)
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
