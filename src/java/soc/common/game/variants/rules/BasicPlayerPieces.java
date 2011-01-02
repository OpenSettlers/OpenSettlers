package soc.common.game.variants.rules;

import soc.common.board.pieces.City;
import soc.common.board.pieces.Road;
import soc.common.board.pieces.Town;
import soc.common.game.GameRules;

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
