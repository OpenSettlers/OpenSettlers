package org.soc.common.game.variants;

import org.soc.common.game.Game;
import org.soc.common.game.variants.rules.AddDiscoveryHex;
import org.soc.common.game.variants.rules.AddGold;
import org.soc.common.game.variants.rules.NewTerritoryBonus;

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
