package org.soc.common.game.variants;

import org.soc.common.game.Game;
import org.soc.common.game.variants.rules.UseBridges;
import org.soc.common.game.variants.rules.UseWalls;

/*
 * A Pioneers ruleset adds two pieces: a wall and a bridge.
 * - A wall can be purchased for 2 clay to increase the maximum amount of cards
 *   for a player by two
 * - A bridge behaves exactly like a road, except that it can be built on water.
 * 
 * Those two pieces can be built in the usual BuildingTurnPhase
 */
@org.soc.common.annotations.Pioneers
public class Pioneers extends AbstractRuleSet
{
    public Pioneers(Game game)
    {
        super(game);

        rules.add(new UseWalls());
        rules.add(new UseBridges());
    }

}
