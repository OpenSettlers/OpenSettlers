package soc.common.game.variants.rules;

import soc.common.board.hexes.ClayHex;
import soc.common.board.hexes.DesertHex;
import soc.common.board.hexes.NoneHex;
import soc.common.board.hexes.OreHex;
import soc.common.board.hexes.RandomHex;
import soc.common.board.hexes.SeaHex;
import soc.common.board.hexes.SheepHex;
import soc.common.board.hexes.TimberHex;
import soc.common.board.hexes.WheatHex;
import soc.common.game.variants.GameRules;

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
