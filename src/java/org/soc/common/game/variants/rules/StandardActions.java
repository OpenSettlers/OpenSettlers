package org.soc.common.game.variants.rules;

import org.soc.common.actions.gameAction.standard.BuildCity;
import org.soc.common.actions.gameAction.standard.BuildRoad;
import org.soc.common.actions.gameAction.standard.BuildTown;
import org.soc.common.actions.gameAction.standard.BuyDevelopmentCard;
import org.soc.common.actions.gameAction.standard.ClaimVictory;
import org.soc.common.actions.gameAction.standard.PlayDevelopmentCard;
import org.soc.common.actions.gameAction.standard.RollDice;
import org.soc.common.actions.gameAction.trading.TradeBank;
import org.soc.common.actions.gameAction.trading.TradePlayer;
import org.soc.common.actions.gameAction.turns.EndTurn;
import org.soc.common.game.variants.GameRules;

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
