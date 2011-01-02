package soc.common.game.variants.rules;

import soc.common.annotations.CitiesKnights;
import soc.common.annotations.Pioneers;
import soc.common.board.pieces.Wall;
import soc.common.game.GameRules;

@CitiesKnights
@Pioneers
public class UseWalls implements GameRule
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
        rules.getPlayablePieces().add(new Wall());
        rules.setStockWallAmount(3);
    }

}
