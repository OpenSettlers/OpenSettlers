package soc.common.game.settings;

import soc.common.views.meta.Meta;

public class ShowChitsAfterPlacing implements GameSetting
{
    private static final long serialVersionUID = 6020672090125579225L;

    @Override
    public void executeGameSetting(GameSettings gameSettings)
    {
        gameSettings.setShowChitsAfterPlacing(this);
    }

    @Override
    public Meta getMeta()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
