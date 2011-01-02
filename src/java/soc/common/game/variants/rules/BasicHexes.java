package soc.common.game.variants.rules;

import soc.common.board.hexes.DesertHex;
import soc.common.board.hexes.NoneHex;
import soc.common.board.hexes.RandomHex;
import soc.common.board.hexes.ResourceHex;
import soc.common.board.hexes.SeaHex;
import soc.common.board.resources.Clay;
import soc.common.board.resources.Ore;
import soc.common.board.resources.Sheep;
import soc.common.board.resources.Timber;
import soc.common.board.resources.Wheat;
import soc.common.game.GameRules;

public class BasicHexes implements GameRule
{

    @Override
    public void set(GameRules rules)
    {
        rules.getHexTypes().add(new ResourceHex(new Timber()));
        rules.getHexTypes().add(new ResourceHex(new Wheat()));
        rules.getHexTypes().add(new ResourceHex(new Ore()));
        rules.getHexTypes().add(new ResourceHex(new Clay()));
        rules.getHexTypes().add(new ResourceHex(new Sheep()));

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
