package soc.common.board.settings;

import java.io.Serializable;

import soc.common.views.meta.HasMeta;

public interface BoardSetting extends HasMeta, Serializable
{
    public void set(BoardSettings boardSettings);
}
