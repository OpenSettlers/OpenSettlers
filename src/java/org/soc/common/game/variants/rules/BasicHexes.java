package org.soc.common.game.variants.rules;

import org.soc.common.board.hexes.ClayHex;
import org.soc.common.board.hexes.DesertHex;
import org.soc.common.board.hexes.NoneHex;
import org.soc.common.board.hexes.OreHex;
import org.soc.common.board.hexes.RandomHex;
import org.soc.common.board.hexes.SeaHex;
import org.soc.common.board.hexes.SheepHex;
import org.soc.common.board.hexes.TimberHex;
import org.soc.common.board.hexes.WheatHex;
import org.soc.common.game.variants.GameRules;

public class BasicHexes implements GameRule
{
    @Override
    public void set(GameRules rules)
    {
        rules.getHexTypes().add(new TimberHex());
        rules.getHexTypes().add(new WheatHex());
        rules.getHexTypes().add(new OreHex());
        rules.getHexTypes().add(new ClayHex());
        rules.getHexTypes().add(new SheepHex());

        rules.getHexTypes().add(new NoneHex());
        rules.getHexTypes().add(new RandomHex());
        rules.getHexTypes().add(new SeaHex());
        rules.getHexTypes().add(new DesertHex());
    }

    @Override
    public String getDescription()
    {
        // TODO fix message
        return null;
    }

}
