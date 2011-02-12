package soc.common.board.resources;

import java.io.Serializable;

public interface Resource extends Serializable
{
    public boolean isTradeable();

    public String getName();

    public String getColor();

    public Resource copy();
}
