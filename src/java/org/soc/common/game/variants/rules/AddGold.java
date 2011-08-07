package org.soc.common.game.variants.rules;

import org.soc.common.annotations.SeaFarers;
import org.soc.common.board.hexes.GoldHex;
import org.soc.common.board.resources.Gold;
import org.soc.common.game.variants.GameRules;

@SeaFarers
public class AddGold implements GameRule
{

    @Override
    public void set(GameRules rules)
    {
        rules.getHexTypes().add(new GoldHex());
        rules.getSupportedResources().add(new Gold());
    }

    @Override
    public String getDescription()
    {
        // TODO fix message
        return null;
    }

}
