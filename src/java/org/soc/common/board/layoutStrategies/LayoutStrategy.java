package org.soc.common.board.layoutStrategies;

import org.soc.common.game.Game;

/* 
 * Prepares a saved board definition into a playable board.
 * 1. Puts hexes from InitialRandomHexes list on RandomHexes
 * 2. Replaces random ports from those out of RandomPorts bag
 * 3. Replaces deserts by volcano/jungles if necessary
 */
public interface LayoutStrategy
{
    public void layoutBoard(Game game);
}
