package soc.common.game.variants;

import soc.common.actions.gameAction.turnActions.EndTurn;
import soc.common.actions.gameAction.turnActions.standard.BuildCity;
import soc.common.actions.gameAction.turnActions.standard.BuildRoad;
import soc.common.actions.gameAction.turnActions.standard.BuildTown;
import soc.common.actions.gameAction.turnActions.standard.BuyDevelopmentCard;
import soc.common.actions.gameAction.turnActions.standard.ClaimVictory;
import soc.common.actions.gameAction.turnActions.standard.PlayDevelopmentCard;
import soc.common.actions.gameAction.turnActions.standard.RollDice;
import soc.common.actions.gameAction.turnActions.standard.TradeBank;
import soc.common.actions.gameAction.turnActions.standard.TradePlayer;
import soc.common.board.pieces.City;
import soc.common.board.pieces.Road;
import soc.common.board.pieces.Town;
import soc.common.board.resources.Clay;
import soc.common.board.resources.Ore;
import soc.common.board.resources.Sheep;
import soc.common.board.resources.Timber;
import soc.common.board.resources.Wheat;
import soc.common.game.Game;
import soc.common.game.IGameRules;
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
    public void setRules()
    {
        IGameRules rules = game.getGameRules();
        // Standard has a largest army playing rule
        game.getGameRules().setEnableLargestArmy(true);

        // Create a stock template
        rules.getStockPieces().add(
                new StockItem().setPiece(new Road()).setAmount(15));
        rules.getStockPieces().add(
                new StockItem().setPiece(new Town()).setAmount(5));
        rules.getStockPieces().add(
                new StockItem().setPiece(new City()).setAmount(4));

        rules.getSupportedResources().add(new Timber());
        rules.getSupportedResources().add(new Wheat());
        rules.getSupportedResources().add(new Ore());
        rules.getSupportedResources().add(new Clay());
        rules.getSupportedResources().add(new Sheep());

        // Add the available actions for standard, in order of TurnPhases

        // First, players might play a victory point or a soldier
        rules.getPossibleActions().add(new PlayDevelopmentCard());

        // Second is always rolling a dice
        rules.getPossibleActions().add(new RollDice());

        // Trading comes before building
        rules.getPossibleActions().add(new TradeBank());
        rules.getPossibleActions().add(new TradePlayer());

        // Build turnphase
        rules.getPossibleActions().add(new BuyDevelopmentCard());
        rules.getPossibleActions().add(new BuildRoad());
        rules.getPossibleActions().add(new BuildTown());
        rules.getPossibleActions().add(new BuildCity());

        // End turn...
        rules.getPossibleActions().add(new EndTurn());

        // Or win game.
        rules.getPossibleActions().add(new ClaimVictory());

        // create standard development cards stack
        game.setDevelopmentCards(DevelopmentCardList.standard());

        // Use a standard dice
        game.getGameRules().setDiceType(new StandardDice());
    }
}
