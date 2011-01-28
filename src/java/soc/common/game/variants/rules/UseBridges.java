package soc.common.game.variants.rules;

import soc.common.annotations.Pioneers;
import soc.common.game.variants.GameRules;

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
        // rules.getPlayablePieces().add(new Bridge());
    }

}
