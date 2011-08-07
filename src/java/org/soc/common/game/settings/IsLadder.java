package org.soc.common.game.settings;

import org.soc.common.views.meta.Meta;

public class IsLadder implements GameSetting
{
    private static final long serialVersionUID = 2506746849067209174L;
    private boolean ladder = true;

    @Override
    public void executeGameSetting(GameSettings gameSettings)
    {
        gameSettings.setIsLadder(this);
    }

    @Override
    public Meta getMeta()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public IsLadder setLadder(boolean ladder)
    {
        this.ladder = ladder;
        return this;
    }

    public boolean isLadder()
    {
        return ladder;
    }

}
