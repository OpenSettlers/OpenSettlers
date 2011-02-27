package soc.common.game.settings;

import java.io.Serializable;

import soc.common.views.meta.HasMeta;

public interface GameSetting extends HasMeta, Serializable
{
    public void executeGameSetting(GameSettings gameSettings);
}
