package org.soc.common.game.variants.rules;

import org.soc.common.board.pieces.City;
import org.soc.common.board.pieces.Road;
import org.soc.common.board.pieces.Town;
import org.soc.common.game.variants.GameRules;

/*
 * Allow a city, town and road to be played with 
 */
public class BasicPlayerPieces implements GameRule
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
        rules.getPlayablePieces().add(new Town());
        rules.getPlayablePieces().add(new City());
        rules.getPlayablePieces().add(new Road());
    }

}
