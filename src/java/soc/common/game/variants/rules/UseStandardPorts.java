package soc.common.game.variants.rules;

import soc.common.board.ports.ThreeToOnePort;
import soc.common.board.ports.TwoToOneResourcePort;
import soc.common.board.resources.Clay;
import soc.common.board.resources.Ore;
import soc.common.board.resources.Sheep;
import soc.common.board.resources.Timber;
import soc.common.board.resources.Wheat;
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
        rules.getSupportedPorts().add(new TwoToOneResourcePort(new Timber()));
        rules.getSupportedPorts().add(new TwoToOneResourcePort(new Wheat()));
        rules.getSupportedPorts().add(new TwoToOneResourcePort(new Ore()));
        rules.getSupportedPorts().add(new TwoToOneResourcePort(new Clay()));
        rules.getSupportedPorts().add(new TwoToOneResourcePort(new Sheep()));
        rules.getSupportedPorts().add(new ThreeToOnePort());
    }

}
