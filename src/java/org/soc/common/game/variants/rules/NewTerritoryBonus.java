package org.soc.common.game.variants.rules;

import org.soc.common.annotations.SeaFarers;
import org.soc.common.board.pieces.IslandBonus;
import org.soc.common.game.variants.GameRules;

/*
 * Players may gain a bonus victory point when they build on a territory
 * the player has not yet built on
 */
@SeaFarers
public class NewTerritoryBonus implements GameRule
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
        rules.getPlayablePieces().add(new IslandBonus());
    }

}
