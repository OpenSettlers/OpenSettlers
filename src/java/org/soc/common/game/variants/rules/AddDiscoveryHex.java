package org.soc.common.game.variants.rules;

import org.soc.common.annotations.SeaFarers;
import org.soc.common.board.hexes.DiscoveryHex;
import org.soc.common.game.variants.GameRules;

@SeaFarers
public class AddDiscoveryHex implements GameRule
{

    @Override
    public void set(GameRules rules)
    {
        rules.getHexTypes().add(new DiscoveryHex());
    }

    @Override
    public String getDescription()
    {
        return "Play with a Discovery Hex, a hex flipped when built adjacent to it";
    }

}
