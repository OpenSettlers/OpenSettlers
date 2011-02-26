package soc.common.board.resources;

import java.io.Serializable;

import soc.common.views.meta.HasMeta;

public interface Resource extends Serializable, HasMeta
{
    public boolean isTradeable();

    public String getName();

    public String getColor();

    public Resource copy();
}
