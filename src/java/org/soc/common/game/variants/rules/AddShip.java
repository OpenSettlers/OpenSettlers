package org.soc.common.game.variants.rules;

import org.soc.common.board.pieces.Ship;
import org.soc.common.game.variants.GameRules;

/*
 * Add support for ships
 */
public class AddShip implements GameRule
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
        rules.getPlayablePieces().add(new Ship());
        rules.setStockShipAmount(15);
    }

}
