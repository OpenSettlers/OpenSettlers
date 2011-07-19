package org.soc.common.game.settings;

import org.soc.common.views.meta.Meta;

public class TradingAfterBuilding implements GameSetting
{
    private static final long serialVersionUID = 4594228612786136611L;

    @Override
    public void executeGameSetting(GameSettings gameSettings)
    {
        gameSettings.setTradingAfterBuilding(this);
    }

    @Override
    public Meta getMeta()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
