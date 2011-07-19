package org.soc.common.game.settings;

import java.io.Serializable;

import org.soc.common.views.meta.HasMeta;


public interface GameSetting extends HasMeta, Serializable
{
    public void executeGameSetting(GameSettings gameSettings);
}
