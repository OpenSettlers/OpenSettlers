package soc.common.game.variants;

import soc.common.actions.gameAction.EndTurn;
import soc.common.actions.gameAction.turnActions.standard.BuildCity;
import soc.common.actions.gameAction.turnActions.standard.BuildRoad;
import soc.common.actions.gameAction.turnActions.standard.BuildTown;
import soc.common.actions.gameAction.turnActions.standard.ClaimVictory;
import soc.common.actions.gameAction.turnActions.standard.PlayDevelopmentCard;
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
import soc.common.game.developmentCards.DevelopmentCardList;
import soc.common.game.dices.StandardDice;

public class Standard extends RuleSet
{
    public Standard(Game game)
    {
        super(game);
    }

    @Override
    public void addBuildablePieces()
    {
        game.getGameRules().getPossibleActions().add(new BuildRoad());
        game.getGameRules().getPossibleActions().add(new BuildCity());
        game.getGameRules().getPossibleActions().add(new BuildTown());
        game.getGameRules().getPossibleActions().add(new EndTurn());
    }

    @Override
    public void setRules()
    {
        game.getGameRules().setEnableLargestArmy(true);
        game.getGameRules().getStockPieces().add
        (
                new StockItem()
                .setPiece(new Road())
                .setAmount(15)
        );
        game.getGameRules().getStockPieces().add
        (
                new StockItem()
                .setPiece(new Town())
                .setAmount(5)
        );
        game.getGameRules().getStockPieces().add
        (
                new StockItem()
                .setPiece(new City())
                .setAmount(4)
        );
        
        game.getGameRules().getPlayableResources().add(new Timber());
        game.getGameRules().getPlayableResources().add(new Wheat());
        game.getGameRules().getPlayableResources().add(new Ore());
        game.getGameRules().getPlayableResources().add(new Clay());
        game.getGameRules().getPlayableResources().add(new Sheep());
        
        addActions();
        
        game.setDevelopmentCards(DevelopmentCardList.standard());
        
        // Use a standard dice
        game.getGameRules().setDiceType(new StandardDice());
    }
    private void addActions()
    {
        game.getGameRules().getPossibleActions().add(new PlayDevelopmentCard());
        game.getGameRules().getPossibleActions().add(new BuildRoad());
        game.getGameRules().getPossibleActions().add(new BuildTown());
        game.getGameRules().getPossibleActions().add(new BuildCity());
        game.getGameRules().getPossibleActions().add(new TradeBank());
        game.getGameRules().getPossibleActions().add(new TradePlayer());
        game.getGameRules().getPossibleActions().add(new EndTurn());
        game.getGameRules().getPossibleActions().add(new ClaimVictory());
    }
}
