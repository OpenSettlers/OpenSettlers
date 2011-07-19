package org.soc.common.game.variants.rules;

import org.soc.common.annotations.Pioneers;
import org.soc.common.game.variants.GameRules;

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
