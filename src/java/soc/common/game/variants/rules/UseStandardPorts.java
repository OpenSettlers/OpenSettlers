package soc.common.game.variants.rules;

import soc.common.board.ports.ClayPort;
import soc.common.board.ports.OrePort;
import soc.common.board.ports.SheepPort;
import soc.common.board.ports.ThreeToOnePort;
import soc.common.board.ports.TimberPort;
import soc.common.board.ports.WheatPort;
import soc.common.game.variants.GameRules;

public class UseStandardPorts implements GameRule
{

    @Override
    public String getDescription()
    {
        // TODO fix message
        return "Use timber, wheat, ore, clay, sheep 2:1 ports and 3:1 port";
    }

    @Override
    public void set(GameRules rules)
    {
        rules.getSupportedPorts().add(new TimberPort());
        rules.getSupportedPorts().add(new WheatPort());
        rules.getSupportedPorts().add(new OrePort());
        rules.getSupportedPorts().add(new ClayPort());
        rules.getSupportedPorts().add(new SheepPort());
        rules.getSupportedPorts().add(new ThreeToOnePort());
    }

}
