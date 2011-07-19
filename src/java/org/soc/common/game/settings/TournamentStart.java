package org.soc.common.game.settings;

import org.soc.common.views.meta.Meta;

public class TournamentStart implements GameSetting
{
    private static final long serialVersionUID = 4004918830693058740L;

    @Override
    public void executeGameSetting(GameSettings gameSettings)
    {
        gameSettings.setTournamentStart(this);
    }

    @Override
    public Meta getMeta()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
