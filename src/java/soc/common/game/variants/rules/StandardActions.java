package soc.common.game.variants.rules;

import soc.common.actions.gameAction.standard.BuildCity;
import soc.common.actions.gameAction.standard.BuildRoad;
import soc.common.actions.gameAction.standard.BuildTown;
import soc.common.actions.gameAction.standard.BuyDevelopmentCard;
import soc.common.actions.gameAction.standard.ClaimVictory;
import soc.common.actions.gameAction.standard.PlayDevelopmentCard;
import soc.common.actions.gameAction.standard.RollDice;
import soc.common.actions.gameAction.trading.TradeBank;
import soc.common.actions.gameAction.trading.TradePlayer;
import soc.common.actions.gameAction.turns.EndTurn;
import soc.common.game.variants.GameRules;

public class StandardActions implements GameRule
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
    }
}
