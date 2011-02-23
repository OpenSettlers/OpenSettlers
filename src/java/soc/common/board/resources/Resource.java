package soc.common.board.resources;

import java.io.Serializable;

import soc.common.ui.meta.HasMeta;

public interface Resource extends Serializable, HasMeta
{
    public boolean isTradeable();

    public String getName();

    public String getColor();

    public Resource copy();
}
