package soc.common.game.variants;

import soc.common.game.Game;
import soc.common.game.variants.rules.AddDiscoveryHex;
import soc.common.game.variants.rules.AddGold;
import soc.common.game.variants.rules.NewTerritoryBonus;

public class SeaFarers extends AbstractRuleSet
{

    public SeaFarers(Game game)
    {
        super(game);

        rules.add(new AddDiscoveryHex());
        rules.add(new NewTerritoryBonus());
        rules.add(new AddGold());
    }
}
