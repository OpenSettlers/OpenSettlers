package soc.common.board.resources;

public interface Resource
{
    public boolean isTradeable();
    public String getName();
    public String getColor();
    public Resource copy();
}
