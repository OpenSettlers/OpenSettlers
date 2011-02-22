package soc.common.game.variants.rules;

import soc.common.annotations.SeaFarers;
import soc.common.board.hexes.GoldHex;
import soc.common.board.resources.Gold;
import soc.common.game.variants.GameRules;

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
