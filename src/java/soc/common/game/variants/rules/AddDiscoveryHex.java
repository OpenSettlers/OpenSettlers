package soc.common.game.variants.rules;

import soc.common.annotations.SeaFarers;
import soc.common.board.hexes.DiscoveryHex;
import soc.common.game.variants.GameRules;

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
