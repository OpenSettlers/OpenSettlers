package soc.common.game.variants.rules;

import soc.common.board.ports.FourToOnePort;
import soc.common.game.variants.GameRules;

/*
 * Every player can trade 4:1 to the bank during {@link PlayTurnsGamePhase}
 */
public class TradeBank41 implements GameRule
{

    @Override
    public String getDescription()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void set(GameRules rules)
    {
        rules.getPortsAtStart().add(new FourToOnePort());
    }

}
