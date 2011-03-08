package soc.common.game.variants;

import soc.common.game.Game;
import soc.common.game.variants.rules.BasicGamePhases;
import soc.common.game.variants.rules.BasicHexes;
import soc.common.game.variants.rules.BasicPlayerPieces;
import soc.common.game.variants.rules.BasicResources;
import soc.common.game.variants.rules.NormalDices;
import soc.common.game.variants.rules.StandardActions;
import soc.common.game.variants.rules.StandardDevelopmentCards;
import soc.common.game.variants.rules.StandardStock;
import soc.common.game.variants.rules.TradeBank41;
import soc.common.game.variants.rules.UseLargestArmy;
import soc.common.game.variants.rules.UseRobber;

public class Standard extends AbstractRuleSet
{
    public Standard(Game game)
    {
        super(game);

        rules.add(new BasicHexes());
        rules.add(new BasicResources());
        rules.add(new BasicPlayerPieces());
        rules.add(new BasicGamePhases());
        rules.add(new StandardActions());

        rules.add(new UseLargestArmy());
        rules.add(new UseRobber());

        rules.add(new StandardStock());
        rules.add(new TradeBank41());

        rules.add(new StandardDevelopmentCards());
        rules.add(new NormalDices());
    }

}
