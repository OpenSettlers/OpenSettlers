package soc.common.game.variants.rules;

import soc.common.annotations.Pioneers;
import soc.common.game.GameRules;

@Pioneers
public class UseBridges implements GameRule
{

    @Override
    public String getDescription()
    {
        // TODO fix message
        return null;
    }

    @Override
    public void set(GameRules rules)
    {
        rules.setStockBridgeAmount(3);
        // TODO: implement bridge
        // rules.getPlayablePieces().add(new Bridge());
    }

}
