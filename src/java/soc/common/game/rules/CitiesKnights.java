package soc.common.game.rules;

import soc.common.board.pieces.Wall;
import soc.common.board.resources.Clay;
import soc.common.board.resources.Ore;
import soc.common.board.resources.ResourceList;
import soc.common.board.resources.Sheep;
import soc.common.board.resources.Timber;
import soc.common.board.resources.Wheat;
import soc.common.game.Game;
import soc.common.game.StockItem;

public class CitiesKnights extends RuleSet 
{

    public CitiesKnights(Game game)
    {
        super(game);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void setRules()
    {
        game.getGameRules().setEnableLargestArmy(false);
        game.getGameRules().getStockPieces().add
        (
                new StockItem()
                .setPiece(new Wall())
                .setAmount(3)
        );
    }

}
