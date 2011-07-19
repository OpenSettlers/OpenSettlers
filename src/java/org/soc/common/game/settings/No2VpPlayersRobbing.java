package org.soc.common.game.settings;

import org.soc.common.views.meta.Meta;

public class No2VpPlayersRobbing implements GameSetting
{
    private static final long serialVersionUID = 4221708430742151374L;
    private boolean no2VpPlayersRobbing = true;

    /**
     * @return the no2VpPlayersRobbing
     */
    public boolean isNo2VpPlayersRobbing()
    {
        return no2VpPlayersRobbing;
    }

    /**
     * @param no2VpPlayersRobbing
     *            the no2VpPlayersRobbing to set
     */
    public No2VpPlayersRobbing setNo2VpPlayersRobbing(
            boolean no2VpPlayersRobbing)
    {
        this.no2VpPlayersRobbing = no2VpPlayersRobbing;
        return this;
    }

    @Override
    public Meta getMeta()
    {
        return null;
    }

    @Override
    public void executeGameSetting(GameSettings gameSettings)
    {
        gameSettings.setNo2vpPlayersRobbing(this);
    }

}
