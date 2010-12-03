package soc.common.game.rules;

import soc.common.actions.gameAction.EndTurn;
import soc.common.actions.gameAction.turnActions.standard.BuildCity;
import soc.common.actions.gameAction.turnActions.standard.BuildRoad;
import soc.common.actions.gameAction.turnActions.standard.BuildTown;
import soc.common.actions.gameAction.turnActions.standard.ClaimVictory;
import soc.common.actions.gameAction.turnActions.standard.TradeBank;
import soc.common.actions.gameAction.turnActions.standard.TradePlayer;
import soc.common.board.pieces.City;
import soc.common.board.pieces.Road;
import soc.common.board.pieces.Ship;
import soc.common.board.pieces.Town;
import soc.common.board.pieces.Wall;
import soc.common.board.resources.Clay;
import soc.common.board.resources.Ore;
import soc.common.board.resources.Sheep;
import soc.common.board.resources.Timber;
import soc.common.board.resources.Wheat;
import soc.common.game.Game;
import soc.common.game.StockItem;

public class Standard extends RuleSet
{

    public Standard(Game game)
    {
        super(game);
    }

    @Override
    public void addBuildablePieces()
    {
        game.getPossibleActions().add(new BuildRoad());
        game.getPossibleActions().add(new BuildCity());
        game.getPossibleActions().add(new BuildTown());
        game.getPossibleActions().add(new EndTurn());
    }

    @Override
    public void setRules()
    {
        game.setEnableLargestArmy(true);
        game.getStockPieces().add
        (
                new StockItem()
                .setPiece(new Road())
                .setAmount(15)
        );
        game.getStockPieces().add
        (
                new StockItem()
                .setPiece(new Town())
                .setAmount(5)
        );
        game.getStockPieces().add
        (
                new StockItem()
                .setPiece(new City())
                .setAmount(4)
        );
        
        game.getPlayableResources().add(new Timber());
        game.getPlayableResources().add(new Wheat());
        game.getPlayableResources().add(new Ore());
        game.getPlayableResources().add(new Clay());
        game.getPlayableResources().add(new Sheep());
        
        addActions();
    }
    private void addActions()
    {
        game.getPossibleActions().add(new BuildRoad());
        game.getPossibleActions().add(new BuildTown());
        game.getPossibleActions().add(new BuildCity());
        game.getPossibleActions().add(new EndTurn());
        game.getPossibleActions().add(new TradeBank());
        game.getPossibleActions().add(new TradePlayer());
        game.getPossibleActions().add(new ClaimVictory());
    }

}
